//
//  BasketViewController.swift
//  MROS
//
//  Created by Tayyip Muslu  on 22.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import UIKit

class BasketViewController: UIViewController, UITableViewDelegate,UITableViewDataSource {
    
    var products: [Product] = [Product]()
    
    @IBOutlet weak var basketTable: UITableView!
    @IBOutlet weak var totalPrice: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        basketTable.delegate = self
        basketTable.dataSource = self
        self.totalPrice.text = "Toplam : 00.00 TL"
        // Do any additional setup after loading the view.
    }
    
    @IBAction func emptyBasket(_ sender: UIButton) {
        // empty basket
    }
    
    @IBAction func basketOK(_ sender: Any) {
        if(self.totalPrice.text == "Toplam : 00.00 TL"){
            // sepet zaten boş
        }else{
            // sipariş alındı..
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.products.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:BasketTableViewCell = self.basketTable.dequeueReusableCell(withIdentifier: "basket") as! BasketTableViewCell
        
        let res: Product = products[indexPath.row]
        
        cell.productCount.text = "\(res.preparation!)"
        cell.productName.text = res.name!
        let result = (Double)(res.preparation!) * res.price!
        cell.productPrice.text = "\(result) TL"
        
        return cell
    }

}
