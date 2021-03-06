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
        
        totalCost()
        // Do any additional setup after loading the view.
    }
    
    override func viewDidAppear(_ animated: Bool) {
        basketTable.reloadData()
        totalCost()
    }
    
    func totalCost() {
        var sum: Double = 0.0
        
        for item in products{
            sum += item.price!
        }
        
        self.totalPrice.text = "Toplam : \(sum) TL"
    }
    
    @IBAction func emptyBasket(_ sender: UIButton) {
        // empty basket
        showAlert()
        products.removeAll()
        self.totalPrice.text = "Toplam : 00.00 TL"
        basketTable.reloadData()
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
    
    func showAlert() {
        let message = "Sepetinizdeki \(products.count) adet ürün kaldırıldı"
        let alert = UIAlertController(title: "Sepeti Boşalt", message: message, preferredStyle: .alert)
        self.present(alert, animated: true, completion: nil)
        Timer.scheduledTimer(withTimeInterval: 2.0, repeats: false, block: { _ in alert.dismiss(animated: true, completion: nil)} )
    }
    
    func showBasketOKAlert() {
        let message = "Sepetinizdeki \(products.count) adet ürün restoranta iletildi, Teşekkür ederiz."
        let alert = UIAlertController(title: "Sepeti Onayı", message: message, preferredStyle: .alert)
        self.present(alert, animated: true, completion: nil)
        Timer.scheduledTimer(withTimeInterval: 3.0, repeats: false, block: { _ in alert.dismiss(animated: true, completion: nil)} )
    }

}
