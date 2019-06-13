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

    private var productsRepository: ProductsRepository? = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.

        productsRepository = ProductsRepository(db: Firestore.firestore())
        
//        let exists = ProductsRepository(db: Firestore.firestore()).exists(identifier: "3BheEX4po6D8kmbh5REc")
        
        productsRepository?.get(identifier: "tm4BBM2ZWZH7GAO86jnL") { (product, error) -> KotlinUnit in
            let i = 0
            
            print("product: \(String(describing: product))")
            
            return KotlinUnit()
        }
        
//        let products = ProductsRepository(db: Firestore.firestore()).get()
        
//        productsRepository?.get { (products, error) -> KotlinUnit in
//            let i = 0
//
//            print("products: \(String(describing: products))")
//
//            return KotlinUnit()
//        }
        
        productsRepository?.get(queryBlock: { (query) -> Query in
//            return query.whereField("name", isEqualTo: "qwerty")
            return query.order(by: "price")
        }, callback: { (products, error) -> KotlinUnit in
            let i = 0
            
            print("products: \(String(describing: products))")
            
            return KotlinUnit()
        })
        
//        ProductsRepository(db: Firestore.firestore()).exists(identifier: "3BheEX4po6D8kmbh5REc") { (exists, error) -> KotlinUnit in
//            let ex = exists as! Bool
//
//            let i = 0
//
//            return KotlinUnit()
//        }
        
//        ProductsRepository(db: Firestore.firestore()).get(identifier: "tm4BBM2ZWZH7GAO86jnL") { (product, error) -> KotlinUnit in
//            let i = 0
//            
//            return KotlinUnit()
//        }
//        
//        ProductsRepository(db: Firestore.firestore()).get() { (products, error) -> KotlinUnit in
//            let i = 0
//            
//            return KotlinUnit()
//        }
    }


}

