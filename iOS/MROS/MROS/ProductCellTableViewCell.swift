//
//  ProductCellTableViewCell.swift
//  MROS
//
//  Created by Tayyip Muslu  on 20.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import UIKit

class ProductCellTableViewCell: UITableViewCell {

    
    @IBOutlet weak var product_add: UIImageView!
    @IBOutlet weak var product_cost: UITextView!
    @IBOutlet weak var product_name: UITextView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
