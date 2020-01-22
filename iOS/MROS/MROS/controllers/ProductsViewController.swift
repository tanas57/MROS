//
//  ProductsViewController.swift
//  MROS
//
//  Created by Tayyip Muslu  on 10.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import UIKit

class ProductsViewController: UIViewController,UITableViewDelegate,UITableViewDataSource {

    var restaurant  = Restaurant()
    var productCats = [ProductCategory]()
    var productList = [Product]()
    
    let add_icon = UIImage(named: "plus.png")
    
    @IBOutlet weak var products: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        self.products.rowHeight = 55
        self.navigationItem.title = "Restorant Ürünleri"
        
        let infoTab = self.tabBarController?.viewControllers![0] as! RestaurantInfoViewController
        self.restaurant = infoTab.restaurant
        print("product tab => \(restaurant.fullName!)")
        products.delegate = self
        products.dataSource = self
        fetchProductCategories()
        
    }
    
    func fetchProductCategories() -> Void {
        if let url = URL(string: "https://mros.api.muslu.net/v1/category/restaurant/\(restaurant.id!)") {
            URLSession.shared.dataTask(with: url) { data, response, error in
                if let data = data {
                    if let jsonString = String(data: data, encoding: .utf8) {
                        print(jsonString)
                    }
                    do{
                        self.productCats = try JSONDecoder().decode([ProductCategory].self, from: data)
                        print("categories has been decoded")
                        print("number of cats => \(self.productCats.count)")
                        
                        self.fetchProducts()
                    }
                    catch let er { print(er.localizedDescription)}
                }
                }.resume()
        }
    }
    
    func fetchProducts() -> Void {
        print("try to fetch products")
        for item in self.productCats {
            if let url = URL(string: "https://mros.api.muslu.net/v1/product/categoryProducts/\(item.id!)") {
                URLSession.shared.dataTask(with: url) { data, response, error in
                    if let data = data {
                        if let jsonString = String(data: data, encoding: .utf8) {
                            print(jsonString)
                        }
                        do{
                            let result = try JSONDecoder().decode([Product].self, from: data)
                            for item in result { self.productList.append(item) }
                            
                            print("category \(item.catName!) products have been decoded")
                            print("number of products => \(self.productList.count)")
                            
                            DispatchQueue.main.async { self.products.reloadData()
                                print("tableview refreshed")
                            }
                            
                        }
                        catch let er { print(er.localizedDescription)}
                    }
                    }.resume()
            }
        }
        
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return productList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell:ProductCellTableViewCell = self.products.dequeueReusableCell(withIdentifier: "product") as! ProductCellTableViewCell
       
        let res: Product = productList[indexPath.row]
        
        cell.product_add.image = add_icon
        cell.product_name.text = res.name!
        cell.product_price.text = "\(res.price!) TL"
        
        return cell
    }
    
    var selectedProduct:Product = Product()
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        selectedProduct = productList[indexPath.row]
        /*
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let nextVC = storyboard.instantiateViewController(withIdentifier: "deneme") as! AddBasketViewController
        nextVC.product = selectedProduct
        let navVC = UINavigationController(rootViewController: nextVC)
        present(navVC, 	animated: false, completion: nil)
        */
        self.performSegue(withIdentifier: "addBasket", sender: nil)
        
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "addBasket" {
            let destinationVC = segue.destination as! AddBasketViewController
            destinationVC.product = selectedProduct
        }
    }
}
