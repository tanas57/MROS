//
//  FeedBack.swift
//  MROS
//
//  Created by Tayyip Muslu  on 22.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import Foundation

struct FeedBack: Codable {
    var id: Int?
    var owner: Customer?
    var restaurant: Restaurant?
    var ratingService: Double?
    var ratingWaiter: Double?
    var ratingFlavor: Double?
    var serviceTime: Double?
    var message: String?
    var feedDateTime: String?
}
