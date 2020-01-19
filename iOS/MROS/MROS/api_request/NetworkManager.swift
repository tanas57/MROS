//
//  NetworkManager.swift
//  MROS
//
//  Created by Tayyip Muslu  on 19.01.2020.
//  Copyright © 2020 MUSLUNET. All rights reserved.
//

import Foundation

class NetworkManager{
    
    static func downloadProfile(completion:((_ json: Data?) -> Void)){
        completion(Data(jsondata.utf8))
    }
    
}

let jsondata = """
{"id":1,"fullName":"Göl Pastanesi","address":"Adatepe, Kıbrıs Cd. No:34, 35400 Buca/İzmir | Yabancı diller fakültesi kampüsü yakını","phone":"0 232 555 3322","logo":null,"photo":"gol-pastanesi.jpg","information":"Pelit ailesi yıllar önce Rize Çamlıhemşin’den Kırım’a çalışmaya gitti. Bölge, pastacılıkta çok ünlüydü. Pastacılığın bütün inceliklerini öğrendikten sonra kendi pastane zincirini kurdu. Ancak Bolşevik İhtilali ile birlikte memleketleri Rize’ye geri döndüler.\r\n\r\n1946’da gittikleri Zonguldak’ta Balkaya Pastanesi’nde çalışmaya başladılar ve kısa bir süre sonra pastanenin işletmesini aldılar. Pastacılığın inceliklerini İstanbul’daki Moskova Pastanesi’nin yetişmiş ustalarından öğrendiler. Zonguldak’ın ardından atılımları için 1954’te İzmir’i tercih ettiler. İzmir Kıbrıs Şehitleri Caddesi’nde önce Ülkü Pastanesi’ni açan Pelit ailesi, 1957 yılında da Sevinç Pastanesi’ni açtılar.\r\n\r\nTürkiye’nin çeşitli şehirlerinden en meşhur pasta, şekerleme, tatlı ustalarını transfer ederek; izmirlileri, baton muzlu pasta, çilekli pasta, turta, kestane şekeri, meyve şekerlemeleri, badem kurabiyesi, dilim pasta, krem şanti ve meyveli pasta gibi yeni lezzetlerle tanıştırdılar. İzmirliler tarafından büyük ilgi gören Sevinç Pastanesi, İzmir’in sembolü ve buluşma noktası haline geldi.\r\n\r\nGünümüzde ailenin ikinci ve üçüncü kuşakları aldıkları bayrağı yeni lezzetler de katarak geleceğe taşımaktadırlar.\r\n\r\nTürkiye’nin neresine gidilirse gidilsin mutlaka Sevinç lezzetlerini tatmış biri vardır.","latitude":37.0001,"longitude":27.0002,"status":true,"orderCount":0,"totalGain":0.0}
"""
