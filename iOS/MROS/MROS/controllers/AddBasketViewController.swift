//
//  AddBasketViewController.swift
//  MROS
//
//  Created by Tayyip Muslu  on 22.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import UIKit

class AddBasketViewController: UIViewController {

    
    var product: Product = Product()
    
    @IBOutlet weak var prep: UILabel!
    @IBOutlet weak var productName: UILabel!
    @IBOutlet weak var price: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()

        self.productName.text = product.name!
        self.prep.text = "\(product.preparation!)"
        self.price.text = "\(product.price!)"
        // Do any additional setup after loading the view.
    }
    
    @IBAction func increase(_ sender: Any) {
        product.preparation! += 1
        let result = (Double)(product.preparation!) * product.price!
        self.price.text = "\(result) TL"
        self.prep.text = "\(product.preparation!)"
    }
    
    @IBAction func decrease(_ sender: Any) {
        if(product.preparation! >= 2){
            product.preparation! -= 1
            let result = (Double)(product.preparation!) * product.price!
            self.price.text = "\(result) TL"
            self.prep.text = "\(product.preparation!)"
        }
    }
    @IBAction func addBasket(_ sender: Any) {
        
    }
}
