package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.CartItem
import com.example.data.model.Product
import com.example.util.KenyanFormatters
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read app name from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Inua Jamii Store", appName)
    }

    @Test
    fun `product tier pricing calculates accurately`() {
        val product = Product(
            id = 1,
            name = "Pembe Maize Meal 2kg",
            category = "Flour & Cereals",
            retailPrice = 210.0,
            wholesalePrice = 175.0,
            wholesaleMinQuantity = 12
        )

        // Below 12 units should be retail price
        assertEquals(210.0, product.getUnitPriceForQuantity(1), 0.001)
        assertEquals(210.0, product.getUnitPriceForQuantity(11), 0.001)
        assertFalse(product.isWholesaleEligible(11))

        // 12 or more units should be wholesale price
        assertEquals(175.0, product.getUnitPriceForQuantity(12), 0.001)
        assertEquals(175.0, product.getUnitPriceForQuantity(20), 0.001)
        assertTrue(product.isWholesaleEligible(12))

        assertEquals(35.0, product.getSavingsPerUnit(), 0.001)
    }

    @Test
    fun `cart item automatically calculates wholesale savings`() {
        val itemRetail = CartItem(
            productId = 1,
            productName = "Pembe 2kg",
            category = "Flour",
            unit = "Bale",
            quantity = 5,
            retailPrice = 210.0,
            wholesalePrice = 175.0,
            wholesaleMinQuantity = 12
        )
        assertFalse(itemRetail.isWholesaleApplied)
        assertEquals(210.0, itemRetail.unitPrice, 0.001)
        assertEquals(1050.0, itemRetail.lineTotal, 0.001)
        assertEquals(0.0, itemRetail.potentialWholesaleSavings, 0.001)

        val itemWholesale = CartItem(
            productId = 1,
            productName = "Pembe 2kg",
            category = "Flour",
            unit = "Bale",
            quantity = 12,
            retailPrice = 210.0,
            wholesalePrice = 175.0,
            wholesaleMinQuantity = 12
        )
        assertTrue(itemWholesale.isWholesaleApplied)
        assertEquals(175.0, itemWholesale.unitPrice, 0.001)
        assertEquals(2100.0, itemWholesale.lineTotal, 0.001)
        assertEquals(420.0, itemWholesale.potentialWholesaleSavings, 0.001)
    }

    @Test
    fun `currency format outputs Kenyan Shillings`() {
        val formatted = KenyanFormatters.formatKsh(1500.0)
        assertEquals("KSh 1,500", formatted)
    }
}
