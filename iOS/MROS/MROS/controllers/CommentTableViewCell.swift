//
//  CommentTableViewCell.swift
//  MROS
//
//  Created by Tayyip Muslu  on 22.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import UIKit

class CommentTableViewCell: UITableViewCell {

    @IBOutlet weak var profile: UIImageView!
    @IBOutlet weak var fullname: UILabel!
    @IBOutlet weak var points: UILabel!
    @IBOutlet weak var message: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
