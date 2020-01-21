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
        cell.product_cost.text = "\(res.price!) TL"
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        /*
        choosenNewspaper = urlnameArray[indexPath.row]
        choosenNewsName = nameArray[indexPath.row]
        performSegue(withIdentifier: "selectNewsPaper", sender: nil)
 */
    }
}
