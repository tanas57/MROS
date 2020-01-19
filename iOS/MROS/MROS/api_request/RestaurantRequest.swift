//
//  RestaurantRequest.swift
//  MROS
//
//  Created by Tayyip Muslu  on 19.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import Foundation

struct RestaurantRequest {
    let resourceURL : URL
    
    init(restaurantID:intmax_t){
        let resourceString = "https://mros.api.muslu.net/v1/restaurant/\(restaurantID)"
        
        guard let resourceURL = URL(string: resourceString) else {
            fatalError()
        }
        
        self.resourceURL = resourceURL
    }
    
    func FetchRestaurant() -> Void {
        
        var jsondata: Data = Data()
        
        URLSession.shared.dataTask(with: resourceURL) { data, response, error in
            if let data = data {
                if let jsonString = String(data: data, encoding: .utf8) {
                    print(jsonString)
                }
                jsondata = data
            }
            }.resume()
        do{
            let res = try JSONDecoder().decode(Restaurant.self, from: jsondata)
            print("Fetched restaurant => " + res.fullName!)
        }
        catch let er { print(er.localizedDescription)}
        //return restaurant
    }

}
