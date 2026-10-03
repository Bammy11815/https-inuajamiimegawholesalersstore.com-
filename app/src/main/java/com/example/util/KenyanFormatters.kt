package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import java.net.URLEncoder
import java.text.NumberFormat
import java.util.Locale

object KenyanFormatters {
    const val STORE_PHONE = "+254783831157"
    const val STORE_WHATSAPP_NUMBER = "254783831157" // E.164 digits without plus for wa.me
    const val STORE_EMAIL = "megawholesalers12@gmail.com"
    const val STORE_NAME = "Inua Jamii Megawholers Store"
    const val STORE_WEBSITE = "https://Inuajamiimegawholesalersstore/"

    fun formatKsh(amount: Double): String {
        val formatter = NumberFormat.getNumberInstance(Locale.US)
        formatter.minimumFractionDigits = 0
        formatter.maximumFractionDigits = 0
        return "KSh " + formatter.format(amount)
    }

    fun openDialer(context: Context, phone: String = STORE_PHONE) {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phone")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open phone dialer: $phone", Toast.LENGTH_SHORT).show()
        }
    }

    fun openWhatsAppChat(
        context: Context,
        message: String = "Hello $STORE_NAME, I would like to inquire about your wholesale and retail products.",
        phone: String = STORE_WHATSAPP_NUMBER
    ) {
        try {
            val encodedMessage = URLEncoder.encode(message, "UTF-8")
            val url = "https://api.whatsapp.com/send?phone=$phone&text=$encodedMessage"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            try {
                // Secondary fallback using wa.me direct browser link
                val encodedMessage = URLEncoder.encode(message, "UTF-8")
                val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$phone?text=$encodedMessage"))
                context.startActivity(fallbackIntent)
            } catch (ex: Exception) {
                Toast.makeText(context, "WhatsApp is not available on this device", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun openEmail(
        context: Context,
        subject: String = "Inquiry - $STORE_NAME",
        body: String = "",
        email: String = STORE_EMAIL
    ) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open email client for $email", Toast.LENGTH_SHORT).show()
        }
    }

    fun generateOrderNumber(): String {
        val randomNum = (1000..9999).random()
        return "IJM-$randomNum"
    }

    fun buildProductWhatsAppMessage(
        productName: String,
        quantity: Int,
        unitPrice: Double,
        isWholesale: Boolean,
        unit: String
    ): String {
        val total = unitPrice * quantity
        val pricingType = if (isWholesale) "Wholesale Deal (12+ units)" else "Retail"
        return """
            Hello $STORE_NAME!
            I would like to place an order via WhatsApp:
            ----------------------------
            • Product: $productName
            • Quantity: $quantity $unit
            • Tier: $pricingType
            • Price: ${formatKsh(unitPrice)} each
            • Total: ${formatKsh(total)}
            ----------------------------
            Kindly confirm availability and delivery to my location. Thank you!
        """.trimIndent()
    }

    fun buildOrderWhatsAppMessage(
        orderNumber: String,
        customerName: String,
        customerPhone: String,
        county: String,
        town: String,
        itemsSummary: String,
        totalAmount: Double,
        paymentMethod: String
    ): String {
        return """
            Hello $STORE_NAME!
            I have placed an order on your mobile app:
            ----------------------------
            Order ID: $orderNumber
            Customer: $customerName
            Phone: $customerPhone
            Delivery To: $town, $county County
            Payment Method: $paymentMethod
            
            Order Items:
            $itemsSummary
            
            Total Amount: ${formatKsh(totalAmount)}
            ----------------------------
            Please confirm receiving this order and let me know when delivery starts.
        """.trimIndent()
    }
}
