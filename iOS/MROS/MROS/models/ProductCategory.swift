//
//  ProductCategory.swift
//  MROS
//
//  Created by Tayyip Muslu  on 19.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import Foundation

struct ProductCategory : Codable{
    var id: Int?
    var catName: String?
    var catIMG: String?
    var restaurant: Restaurant?
    var status:Bool?
    var counter: Int?
}
