//
//  ProductsViewController.swift
//  MROS
//
//  Created by Tayyip Muslu  on 10.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import UIKit

class ProductsViewController: UIViewController {

    var restaurant  = Restaurant()
    var productCats = [ProductCategory]()
    var productList = [Product]()
    
    @IBOutlet weak var products: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        if let url = URL(string: "https://mros.api.muslu.net/v1/restaurant/1") {
            URLSession.shared.dataTask(with: url) { data, response, error in
                if let data = data {
                    if let jsonString = String(data: data, encoding: .utf8) {
                        print(jsonString)
                    }
                    self.retreiveData(data)
                }
                }.resume()
        }
        
    }
    
    func retreiveData(_ data:Data) -> Void {
        do{
            restaurant = try JSONDecoder().decode(Restaurant.self, from: data)
            print("deneme")
            print(restaurant.fullName!)
        }
        catch let er { print(er.localizedDescription)}
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return productList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell:UITableViewCell = self.newspapers.dequeueReusableCell(withIdentifier: "cell") as! UITableViewCell
        /*let image = imageArray[indexPath.row]
        
        cell.textLabel?.text = self.nameArray[indexPath.row]
        cell.imageView?.image = image
        */
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
