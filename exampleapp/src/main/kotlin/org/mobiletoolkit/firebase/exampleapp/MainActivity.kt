package org.mobiletoolkit.firebase.exampleapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.mobiletoolkit.firebase.exampleapp.firestore.Product
import org.mobiletoolkit.firebase.exampleapp.firestore.ProductsRepository

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val productsRepository: ProductsRepository by lazy {
        ProductsRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

//        productsRepository.get("wrong_id") { product, error ->
//            Log.v("ProductsRepository", "get product: $product | error: $error")
//        }
//
//        productsRepository.get("tm4BBM2ZWZH7GAO86jnL") { product, error ->
//            Log.v("ProductsRepository", "get product: $product | error: $error")
//        }
//
//        productsRepository.observe("tm4BBM2ZWZH7GAO86jnL") { product, error ->
//            Log.v("ProductsRepository", "observe product: $product | error: $error")
//        }
//
//        productsRepository.observe("oUFKwGYbNKlIZozrfh9O") { product, error ->
//            Log.v("ProductsRepository", "observe product: $product | error: $error")
//        }
//
//        productsRepository.get({
////            it.whereEqualTo("name", "qwerty")
//            it.orderBy("price")
//        }, { products, error ->
//            Log.v("ProductsRepository", "get products: $products | error: $error")
//        })
//
//        productsRepository.get { products, error ->
//            Log.v("ProductsRepository", "get products: $products | error: $error")
//        }

//        val observerReference1 = productsRepository.observe({
////            it.whereEqualTo("name", "qwerty")
//            it.orderBy("price")
//        }, { products, error ->
//            Log.v("ProductsRepository", "observe products: $products | error: $error")
//        })
//
//        val observerReference2 = productsRepository.observe { products, error ->
//            Log.v("ProductsRepository", "observe products: $products | error: $error")
//        }
//
//        observerReference2.stop()

//        productsRepository.create(Product("product-name", "product-description", 34.76)) { product, error ->
//            Log.v("ProductsRepository", "create product: $product | error: $error")
//        }
//
        productsRepository.create(Product("test-name", "test-description", 1234.76), "test") { product, error ->
            Log.v("ProductsRepository", "create product: $product | error: $error")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        productsRepository.stopAllObservers()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
