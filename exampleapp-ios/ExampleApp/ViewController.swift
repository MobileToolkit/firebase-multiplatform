//
//  ViewController.swift
//  ExampleApp
//
//  Created by Sebastian Owodzin on 15/04/2019.
//  Copyright © 2019 mobiletoolkit.org. All rights reserved.
//

import FirebaseFirestore
import MTFirestore
import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.

        ProductsRepository(db: Firestore.firestore()).exists(identifier: "tm4BBM2ZWZH7GAO86jnL") { (exists, error) -> KotlinUnit in
            let ex = exists as! Bool

            let i = 0

            return KotlinUnit()
        }
    }


}

