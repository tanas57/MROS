//
//  Product.swift
//  MROS
//
//  Created by Tayyip Muslu  on 19.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import Foundation


struct Product : Codable {
    var id: Int?
    var name:String?
    var price:Double?
    var img:String?
    var restaurant:Restaurant?
    var productCategory:ProductCategory?
    var isPortionable:Bool?
    var preparation:Int?
}
