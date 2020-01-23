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
    
    @IBOutlet weak var indicator: UIActivityIndicatorView!
    @IBOutlet weak var restorantID: UITextField!
    @IBOutlet weak var restaurantName: UILabel!
    
    @IBAction func restaurantFetch(_ sender: Any) {
        
        self.indicator.startAnimating()
        print("clicked => \(restorantID.text!)")
        if let url = URL(string: "https://mros.api.muslu.net/v1/restaurant/\(restorantID.text!)") {
            URLSession.shared.dataTask(with: url) { data, response, error in
                
                if let data = data {
                    if let jsonString = String(data: data, encoding: .utf8) {
                        print(jsonString)
                    }
                    self.retreiveData(data)
                }
                }.resume()
        }
        indicator.stopAnimating()
        
    }
    @IBAction func restaurantlogin(_ sender: Any) {
        
        performSegue(withIdentifier: "restaurantLogin", sender: nil)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        indicator.hidesWhenStopped = true
        
        
    }
    
    func retreiveData(_ data:Data) -> Void {
        do{
            restaurant = try JSONDecoder().decode(Restaurant.self, from: data)
            print(restaurant.fullName!)
            DispatchQueue.main.async {
                self.restaurantName.text = self.restaurant.fullName!
            }
            
        }
        catch let er { print(er.localizedDescription)}
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "restaurantLogin" {
            let destinationVC = segue.destination as! TabViewController
            let desView: RestaurantInfoViewController = destinationVC.viewControllers?.first as! RestaurantInfoViewController
            
            desView.restaurant = self.restaurant
        }
    }
}
