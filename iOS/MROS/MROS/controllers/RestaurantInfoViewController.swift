//
//  RestaurantInfoViewController.swift
//  MROS
//
//  Created by Tayyip Muslu  on 21.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import UIKit

class RestaurantInfoViewController: UIViewController {

    
    var restaurant:Restaurant = Restaurant()
    
    @IBOutlet weak var restorantname: UILabel!
    @IBOutlet weak var indicator: UIActivityIndicatorView!
    @IBOutlet weak var phone: UITextView!
    @IBOutlet weak var address: UITextView!
    @IBOutlet weak var info: UITextView!
    @IBOutlet weak var image: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.indicator.hidesWhenStopped = true
        self.phone.text = restaurant.phone!
        self.address.text = restaurant.address!
        self.info.text = restaurant.information!
        self.restorantname.text = "\(restaurant.fullName!) - Hoşgeldiniz"
        
        let url = URL(string: "https://mros.api.muslu.net/v1/restaurant/image/\(restaurant.id!)")!
        //
        downloadImage(url)
        /*
        if let url = URL(string: "https://mros.api.muslu.net/v1/restaurant/image/\(restaurant.id!)") {
            URLSession.shared.dataTask(with: url) { data, response, error in
                
                if let data = data {
                    let img = UIImage(data: data)
                    self.image.image = img
                }
                }.resume()
        }
        */
        
        
        
        // Do any additional setup after loading the view.
    }
    
    func getData(from url: URL, completion: @escaping (Data?, URLResponse?, Error?) -> ()) {
        URLSession.shared.dataTask(with: url, completionHandler: completion).resume()
    }
    
    func downloadImage(_ url: URL) {
        print("Download Started")
        indicator.startAnimating()
        getData(from: url) { data, response, error in
            guard let data = data, error == nil else { return }
            print("Download Finished")
            DispatchQueue.main.async() {
                let img = UIImage(data: data)
                self.image.image = img
                self.indicator.stopAnimating()
            }
        }
    }

  

}
