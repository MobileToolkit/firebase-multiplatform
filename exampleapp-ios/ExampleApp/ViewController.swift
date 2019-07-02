//
//  ViewController.swift
//  ExampleApp
//
//  Created by Sebastian Owodzin on 15/04/2019.
//  Copyright © 2019 mobiletoolkit.org. All rights reserved.
//

import ExampleAppShared
import FirebaseFirestore
import UIKit

class ViewController: UIViewController {

    private var productsRepository: ProductsRepository!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.

        productsRepository = ProductsRepository(db: Firestore.firestore())

        productsRepository.get(identifier: "tm4BBM2ZWZH7GAO86jnL") { (product, error) -> Void in
            print("product: \(String(describing: product))")
        }
        
        productsRepository.observe(identifier: "tm4BBM2ZWZH7GAO86jnL") { (product, error) -> Void in
            print("product: \(String(describing: product))")
        }

        productsRepository.get(queryBlock: { (query) -> Query in
//            return query.whereField("name", isEqualTo: "qwerty")
            return query.order(by: "price")
        }, callback: { (products, error) -> Void in
            print("products: \(String(describing: products))")
        })
        
        productsRepository.get { (products, error) -> Void in
            print("products: \(String(describing: products))")
        }

        productsRepository.observe { (products, error) -> Void in
            print("products: \(String(describing: products))")
        }
    }
}

