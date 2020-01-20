//
//  ViewController.swift
//  MROS
//
//  Created by Tayyip Muslu  on 19.12.2019.
//  Copyright © 2019 MUSLUNET. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    var restaurant:Restaurant = Restaurant()
    
    @IBAction func restaurantlogin(_ sender: Any) {
        
        performSegue(withIdentifier: "restaurantLogin", sender: nil)
    }
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
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "restaurantLogin" {
            let destinationVC = segue.destination as! ProductsViewController
            destinationVC.restaurant = self.restaurant
        }
    }
}
