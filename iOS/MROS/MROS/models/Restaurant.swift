//
//  Restaurant.swift
//  MROS
//
//  Created by Tayyip Muslu  on 19.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import Foundation

struct Restaurant: Codable {
    var id:Int?
    var fullName:String?
    var address:String?
    var phone:String?
    var logo:String?
    var photo:String?
    var information:String?
    var latitude:Double?
    var longitude:Double?
    var status:Bool?
    var orderCount:Int?
    var totalGain:Double?
    
}
