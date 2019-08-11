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

    private lazy var productsRepository:  ProductsRepository = {
        return  ProductsRepository()
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.

//        productsRepository.get(identifier: "wrong_id") { (product, error) -> Void in
//            print("ProductsRepository::get product: \(String(describing: product)) | error: \(String(describing: error))")
//        }
//
        productsRepository.get(identifier: "tm4BBM2ZWZH7GAO86jnL") { (product, error) -> Void in
            print("ProductsRepository::get product: \(String(describing: product)) | error: \(String(describing: error))")
        }
//
//        productsRepository.get(identifier: "oUFKwGYbNKlIZozrfh9O") { (product, error) -> Void in
//            print("ProductsRepository::get product: \(String(describing: product)) | error: \(String(describing: error))")
//        }
//
//        productsRepository.observe(identifier: "tm4BBM2ZWZH7GAO86jnL") { (product, error) -> Void in
//            print("ProductsRepository::observe product: \(String(describing: product)) | error: \(String(describing: error))")
//        }
//
//        productsRepository.get(queryBlock: { (query) -> Query in
////            return query.whereField("name", isEqualTo: "qwerty")
//            return query.order(by: "price")
//        }, callback: { (products, error) -> Void in
//            print("ProductsRepository::get products: \(String(describing: products)) | error: \(String(describing: error))")
//        })
//
//        productsRepository.get { (products, error) -> Void in
//            print("ProductsRepository::get products: \(String(describing: products)) | error: \(String(describing: error))")
//        }

//        let observerReference1 = productsRepository.observe(queryBlock: { (query) -> Query in
////            return query.whereField("name", isEqualTo: "qwerty")
//            return query.order(by: "price")
//        }, listener: { (products, error) -> Void in
//            print("ProductsRepository::observe products: \(String(describing: products)) | error: \(String(describing: error))")
//        })
//
//        let observerReference2 = productsRepository.observe(listener: { (products, error) -> Void in
//            print("ProductsRepository::observe products: \(String(describing: products)) | error: \(String(describing: error))")
//        })
//
//        observerReference2.stop()
        
//        productsRepository.create(entity: Product(name: "product-name", description: "product-description", price: 34.76), identifier: nil) { (product, error) -> Void in
//            print("ProductsRepository::create product: \(String(describing: product)) | error: \(String(describing: error))")
//        }
//
//        productsRepository.create(entity: Product(name: "test-name", description: "test-description", price: 1234.76), identifier: "test") { (product, error) -> Void in
//            print("ProductsRepository::create product: \(String(describing: product)) | error: \(String(describing: error))")
//        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        productsRepository.stopAllObservers()
    }
}

