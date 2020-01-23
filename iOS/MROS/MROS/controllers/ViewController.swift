//
//  ViewController.swift
//  MROS
//
//  Created by Tayyip Muslu  on 19.12.2019.
//  Copyright © 2019 MUSLUNET. All rights reserved.
//

import UIKit
import AVFoundation

class ViewController: UIViewController, AVCaptureMetadataOutputObjectsDelegate {
    var video = AVCaptureVideoPreviewLayer()
    var captureSession: AVCaptureSession!
    var previewLayer: AVCaptureVideoPreviewLayer!
    
    var qrCode = ""
    
    var restaurant:Restaurant = Restaurant()
    
    @IBOutlet weak var indicator: UIActivityIndicatorView!
    @IBOutlet weak var restorantID: UITextField!
    @IBOutlet weak var restaurantName: UILabel!
    @IBOutlet weak var image: UIImageView!
    
    @IBAction func restaurantFetch(_ sender: Any) {
        
        let res_id: String = restorantID.text!
        readRestaurantInfo(res_id)
        
    }
    @IBAction func restaurantlogin(_ sender: Any) {
        
        performSegue(withIdentifier: "restaurantLogin", sender: nil)
    }
    
    func readRestaurantInfo(_ info: String){
        
        self.indicator.startAnimating()
        print("clicked => \(restorantID.text!)")
        if let url = URL(string: "https://mros.api.muslu.net/v1/restaurant/\(info)") {
            URLSession.shared.dataTask(with: url) { data, response, error in
                
                if let data = data {
                    if let jsonString = String(data: data, encoding: .utf8) {
                        print(jsonString)
                    }
                    self.retreiveData(data)
                }
                }.resume()
        }
        
    }
    
    func retreiveData(_ data:Data) -> Void {
        do{
            restaurant = try JSONDecoder().decode(Restaurant.self, from: data)
            print(restaurant.fullName!)
            DispatchQueue.main.async {
                self.restaurantName.text = self.restaurant.fullName!
                self.restaurantlogin(self)
                self.indicator.stopAnimating()
            }
            
        }
        catch let er { print(er.localizedDescription)}
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        indicator.hidesWhenStopped = true
        
        captureSession = AVCaptureSession()
        
        guard let videoCaptureDevice = AVCaptureDevice.default(for: .video) else { return }
        let videoInput: AVCaptureDeviceInput
        
        do {
            videoInput = try AVCaptureDeviceInput(device: videoCaptureDevice)
        } catch {
            return
        }
        
        if (captureSession.canAddInput(videoInput)) {
            captureSession.addInput(videoInput)
        } else {
            failed()
            return
        }
        
        let metadataOutput = AVCaptureMetadataOutput()
        
        if (captureSession.canAddOutput(metadataOutput)) {
            captureSession.addOutput(metadataOutput)
            
            metadataOutput.setMetadataObjectsDelegate(self, queue: DispatchQueue.main)
            metadataOutput.metadataObjectTypes = [.qr]
        } else {
            failed()
            return
        }
        
        previewLayer = AVCaptureVideoPreviewLayer(session: captureSession)
        previewLayer.frame = view.layer.bounds
        previewLayer.videoGravity = .resizeAspectFill
        view.layer.addSublayer(previewLayer)
        
        captureSession.startRunning()
        
    }
    
    func failed() {
        let ac = UIAlertController(title: "Scanning not supported", message: "Your device does not support scanning a code from an item. Please use a device with a camera.", preferredStyle: .alert)
        ac.addAction(UIAlertAction(title: "OK", style: .default))
        present(ac, animated: true)
        captureSession = nil
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        if (captureSession?.isRunning == false) {
            captureSession.startRunning()
        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        if (captureSession?.isRunning == true) {
            captureSession.stopRunning()
        }
    }
    
    func found(code: String) {
        print(code)
    }
    
    func metadataOutput(_ output: AVCaptureMetadataOutput, didOutput metadataObjects: [AVMetadataObject], from connection: AVCaptureConnection) {
        //captureSession.stopRunning()
        
        if let metadataObject = metadataObjects.first {
            guard let readableObject = metadataObject as? AVMetadataMachineReadableCodeObject else { return }
            guard let stringValue = readableObject.stringValue else { return }
            print(qrCode)
            readRestaurantInfo(qrCode)
            //found(code: stringValue)
        }
        //        dismiss(animated: true)
    }
    
    override var prefersStatusBarHidden: Bool {
        return true
    }
    
    override var supportedInterfaceOrientations: UIInterfaceOrientationMask {
        return .portrait
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "restaurantLogin" {
            let destinationVC = segue.destination as! TabViewController
            let desView: RestaurantInfoViewController = destinationVC.viewControllers?.first as! RestaurantInfoViewController
            
            desView.restaurant = self.restaurant
        }
    }
}
