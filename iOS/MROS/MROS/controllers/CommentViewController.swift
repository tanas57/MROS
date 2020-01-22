//
//  CommentViewController.swift
//  MROS
//
//  Created by Tayyip Muslu  on 22.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import UIKit

class CommentViewController: UIViewController,UITableViewDelegate,UITableViewDataSource {
    
    @IBOutlet weak var commentTable: UITableView!
    @IBOutlet weak var indicator: UIActivityIndicatorView!
    
    var restaurant: Restaurant = Restaurant()
    var comments : [FeedBack] = [FeedBack]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.indicator.hidesWhenStopped = true
        self.commentTable.rowHeight = 150
        
        let infoTab = self.tabBarController?.viewControllers![0] as! RestaurantInfoViewController
        self.restaurant = infoTab.restaurant
        print("comment tab => \(restaurant.fullName!)")
        
        commentTable.delegate = self
        commentTable.dataSource = self
        
        let url = URL(string: "https://mros.api.muslu.net/v1/feeds/restaurant/\(restaurant.id!)")!
        downloadImage(url)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return comments.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:CommentTableViewCell = self.commentTable.dequeueReusableCell(withIdentifier: "comment") as! CommentTableViewCell
        
        let res: FeedBack = comments[indexPath.row]
        
        cell.fullname.text = res.owner?.fullName!
        cell.points.text = "Servis \(res.ratingService!) Hız \(res.serviceTime!) Lezzet \(res.ratingFlavor!) Garson \(res.ratingWaiter!)"
        cell.message.text = res.message!
        
        return cell
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
                do{
                    self.comments = try JSONDecoder().decode([FeedBack].self, from: data)
                    print("comments has been decoded")
                    print("number of comments => \(self.comments.count)")
                    for item in self.comments{
                        print(item.owner?.fullName!)
                    }
                }
                catch let ex { print(ex.localizedDescription)}
                DispatchQueue.main.async {
                    self.commentTable.reloadData()
                    print("tableview refreshed")
                    self.indicator.stopAnimating()
                }
            }
        }
    }
    
}
