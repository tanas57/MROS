//
//  TabViewController.swift
//  MROS
//
//  Created by Tayyip Muslu  on 21.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import UIKit

class TabViewController: UITabBarController {

    var restaurant:Restaurant = Restaurant()
    var orderProducts: [Product] = [Product]()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
