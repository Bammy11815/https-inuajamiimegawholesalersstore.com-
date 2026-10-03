package com.example.data.db

import com.example.data.model.Product

object DefaultProductsSeed {
    val initialProducts: List<Product> = listOf(
        // Flour & Cereals
        Product(
            id = 1,
            name = "Pembe Maize Meal 2kg",
            category = "Flour & Cereals",
            retailPrice = 210.0,
            wholesalePrice = 175.0,
            wholesaleMinQuantity = 12,
            unit = "Bale / 2kg Pkt",
            stockQuantity = 450,
            inStock = true,
            description = "Grade 1 fortified sifted maize meal. Kenya's favorite ugali flour. Bale contains 12 x 2kg packets.",
            imageUrl = "https://images.unsplash.com/photo-1586201375761-83865001e31c?auto=format&fit=crop&w=600&q=80",
            isFeatured = true,
            badge = "Wholesale Favorite"
        ),
        Product(
            id = 2,
            name = "Jogoo Maize Flour 2kg",
            category = "Flour & Cereals",
            retailPrice = 205.0,
            wholesalePrice = 170.0,
            wholesaleMinQuantity = 12,
            unit = "2kg Packet",
            stockQuantity = 320,
            inStock = true,
            description = "Finely milled fortified maize meal for rich smooth ugali. Wholesale pack contains 12 packets.",
            imageUrl = "https://images.unsplash.com/photo-1574323347407-f5e1ad6d020b?auto=format&fit=crop&w=600&q=80",
            isFeatured = true,
            badge = "Best Seller"
        ),
        Product(
            id = 3,
            name = "Ndovu Home Baking Flour 2kg",
            category = "Flour & Cereals",
            retailPrice = 225.0,
            wholesalePrice = 188.0,
            wholesaleMinQuantity = 12,
            unit = "2kg Packet",
            stockQuantity = 210,
            inStock = true,
            description = "Premium all-purpose wheat flour for mandazi, chapati, pastries, and baking. 12 units per bale.",
            imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?auto=format&fit=crop&w=600&q=80",
            isFeatured = false,
            badge = "Hot Deal"
        ),
        Product(
            id = 4,
            name = "Daawat Long Grain Basmati Rice 5kg",
            category = "Food & Groceries",
            retailPrice = 1350.0,
            wholesalePrice = 1180.0,
            wholesaleMinQuantity = 6,
            unit = "5kg Bag",
            stockQuantity = 180,
            inStock = true,
            description = "Aromatic traditional long-grain basmati rice. Fluffy, non-sticky and flavorful for pilau and biryani.",
            imageUrl = "https://images.unsplash.com/photo-1586201375761-83865001e31c?auto=format&fit=crop&w=600&q=80",
            isFeatured = true,
            badge = "Top Pick"
        ),

        // Cooking Oil
        Product(
            id = 5,
            name = "Rina Pure Vegetable Cooking Oil 5L",
            category = "Cooking Oil",
            retailPrice = 1280.0,
            wholesalePrice = 1090.0,
            wholesaleMinQuantity = 4,
            unit = "5L Jerrycan",
            stockQuantity = 140,
            inStock = true,
            description = "Fortified with Vitamin A & D. Triple refined palm olein cooking oil, clean frying with zero cholesterol.",
            imageUrl = "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5?auto=format&fit=crop&w=600&q=80",
            isFeatured = true,
            badge = "Wholesale Deal"
        ),
        Product(
            id = 6,
            name = "Salit Cooking Oil 10L",
            category = "Cooking Oil",
            retailPrice = 2450.0,
            wholesalePrice = 2150.0,
            wholesaleMinQuantity = 2,
            unit = "10L Jerrycan",
            stockQuantity = 85,
            inStock = true,
            description = "Heavy-duty commercial and household vegetable cooking oil. Ideal for commercial eateries and large families.",
            imageUrl = "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5?auto=format&fit=crop&w=600&q=80",
            isFeatured = true,
            badge = "Bulk Special"
        ),
        Product(
            id = 7,
            name = "Golden Fry Vegetable Cooking Oil 3L",
            category = "Cooking Oil",
            retailPrice = 820.0,
            wholesalePrice = 695.0,
            wholesaleMinQuantity = 6,
            unit = "3L Bottle",
            stockQuantity = 190,
            inStock = true,
            description = "Refined pure vegetable oil, heart-friendly, non-foaming and high smoking point.",
            imageUrl = "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5?auto=format&fit=crop&w=600&q=80",
            isFeatured = false,
            badge = null
        ),

        // Sugar & Salt
        Product(
            id = 8,
            name = "Mumias Pure White Sugar 1kg",
            category = "Sugar & Salt",
            retailPrice = 175.0,
            wholesalePrice = 145.0,
            wholesaleMinQuantity = 20,
            unit = "1kg Pkt / Bale 20kg",
            stockQuantity = 500,
            inStock = true,
            description = "Crystalline pure cane sugar, sweet and quick dissolving. Bale contains 20 x 1kg packets.",
            imageUrl = "https://images.unsplash.com/photo-1622484216805-4c02eb962f2f?auto=format&fit=crop&w=600&q=80",
            isFeatured = true,
            badge = "Essential"
        ),
        Product(
            id = 9,
            name = "Kensalt Iodized Table Salt 1kg",
            category = "Sugar & Salt",
            retailPrice = 45.0,
            wholesalePrice = 34.0,
            wholesaleMinQuantity = 20,
            unit = "1kg Pkt / Bale 20kg",
            stockQuantity = 600,
            inStock = true,
            description = "Vacuum refined iodized table salt. Promotes healthy thyroid function and enhances every meal.",
            imageUrl = "https://images.unsplash.com/photo-1518110903416-749e7b26d8ee?auto=format&fit=crop&w=600&q=80",
            isFeatured = false,
            badge = null
        ),

        // Tea & Spices
        Product(
            id = 10,
            name = "Ketepa Pride Tea Bags 100s",
            category = "Tea & Spices",
            retailPrice = 280.0,
            wholesalePrice = 230.0,
            wholesaleMinQuantity = 12,
            unit = "Box of 100",
            stockQuantity = 230,
            inStock = true,
            description = "Rich, aromatic Kenyan highland black tea bags. Carton of 12 boxes.",
            imageUrl = "https://images.unsplash.com/photo-1576092768241-dec231879fc3?auto=format&fit=crop&w=600&q=80",
            isFeatured = true,
            badge = "Best Seller"
        ),
        Product(
            id = 11,
            name = "Royco Mchuzi Mix Beef 500g",
            category = "Tea & Spices",
            retailPrice = 230.0,
            wholesalePrice = 195.0,
            wholesaleMinQuantity = 12,
            unit = "500g Jar",
            stockQuantity = 160,
            inStock = true,
            description = "Authentic Kenyan stew flavor enhancer with aromatic herbs and spices. Thickens and flavors stews.",
            imageUrl = "https://images.unsplash.com/photo-1596040033229-a9821ebd058d?auto=format&fit=crop&w=600&q=80",
            isFeatured = false,
            badge = "Kenyan Classic"
        ),

        // Beverages
        Product(
            id = 12,
            name = "Brookside Long Life Milk 500ml (Crate of 24)",
            category = "Beverages",
            retailPrice = 1680.0,
            wholesalePrice = 1450.0,
            wholesaleMinQuantity = 3,
            unit = "Crate 24 Pkts",
            stockQuantity = 90,
            inStock = true,
            description = "UHT homogenised whole cow milk. Fresh dairy goodness with extended shelf life without refrigeration.",
            imageUrl = "https://images.unsplash.com/photo-1550583724-b2692b85b150?auto=format&fit=crop&w=600&q=80",
            isFeatured = true,
            badge = "Wholesale Crate"
        ),
        Product(
            id = 13,
            name = "Coca-Cola 500ml Pet (Pack of 24)",
            category = "Beverages",
            retailPrice = 1440.0,
            wholesalePrice = 1260.0,
            wholesaleMinQuantity = 4,
            unit = "Pack 24 Bottles",
            stockQuantity = 120,
            inStock = true,
            description = "Refreshing chilled sparkling soda. Shrink-wrapped case of 24 x 500ml bottles.",
            imageUrl = "https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&w=600&q=80",
            isFeatured = false,
            badge = null
        ),

        // Cleaning Products
        Product(
            id = 14,
            name = "Omo Hand Washing Powder 1kg",
            category = "Cleaning Products",
            retailPrice = 330.0,
            wholesalePrice = 275.0,
            wholesaleMinQuantity = 10,
            unit = "1kg Pkt",
            stockQuantity = 220,
            inStock = true,
            description = "Fast stain removal detergent powder with extra foam and refreshing fragrance. Tough on stains, gentle on hands.",
            imageUrl = "https://images.unsplash.com/photo-1583947215259-38e31be8751f?auto=format&fit=crop&w=600&q=80",
            isFeatured = true,
            badge = "Hot Deal"
        ),
        Product(
            id = 15,
            name = "Sunlight Dishwashing Liquid Lemon 750ml",
            category = "Cleaning Products",
            retailPrice = 260.0,
            wholesalePrice = 215.0,
            wholesaleMinQuantity = 12,
            unit = "750ml Bottle",
            stockQuantity = 175,
            inStock = true,
            description = "With real lemon extract for superior grease cutting power. Sparkly clean plates with pleasant aroma.",
            imageUrl = "https://images.unsplash.com/photo-1584813470613-5b1c1cad3d69?auto=format&fit=crop&w=600&q=80",
            isFeatured = false,
            badge = null
        ),
        Product(
            id = 16,
            name = "Menengai Cream Bar Soap 800g",
            category = "Cleaning Products",
            retailPrice = 190.0,
            wholesalePrice = 155.0,
            wholesaleMinQuantity = 25,
            unit = "800g Bar / Box 25",
            stockQuantity = 340,
            inStock = true,
            description = "Multipurpose laundry bar soap. Long-lasting, rich lather for clothes, utensils, and daily cleaning.",
            imageUrl = "https://images.unsplash.com/photo-1607082348824-0a96f2a4b9da?auto=format&fit=crop&w=600&q=80",
            isFeatured = true,
            badge = "Value Bar"
        ),

        // Personal Care
        Product(
            id = 17,
            name = "Geisha Bathing Soap 225g x 3 Multipack",
            category = "Personal Care",
            retailPrice = 320.0,
            wholesalePrice = 270.0,
            wholesaleMinQuantity = 12,
            unit = "Pack of 3 Bars",
            stockQuantity = 180,
            inStock = true,
            description = "Gentle family beauty soap infused with natural extracts. Nourishes and cleanses skin for hours.",
            imageUrl = "https://images.unsplash.com/photo-1607082348824-0a96f2a4b9da?auto=format&fit=crop&w=600&q=80",
            isFeatured = false,
            badge = null
        ),
        Product(
            id = 18,
            name = "Colgate Maximum Cavity Protection 140g",
            category = "Personal Care",
            retailPrice = 210.0,
            wholesalePrice = 175.0,
            wholesaleMinQuantity = 24,
            unit = "140g Tube",
            stockQuantity = 290,
            inStock = true,
            description = "Fluoride toothpaste for strong teeth, fresh breath, and active cavity defense.",
            imageUrl = "https://images.unsplash.com/photo-1559599101-f09722fb4948?auto=format&fit=crop&w=600&q=80",
            isFeatured = true,
            badge = "Top Brand"
        ),

        // Household Products
        Product(
            id = 19,
            name = "Velvex Soft Toilet Tissue 10 Rolls Pack",
            category = "Household Products",
            retailPrice = 540.0,
            wholesalePrice = 450.0,
            wholesaleMinQuantity = 6,
            unit = "Pack of 10 Rolls",
            stockQuantity = 150,
            inStock = true,
            description = "2-ply ultra-soft, absorbent embossed bathroom tissue. Virgin wood pulp, hygienic and skin safe.",
            imageUrl = "https://images.unsplash.com/photo-1584556812952-905ffd0c611a?auto=format&fit=crop&w=600&q=80",
            isFeatured = true,
            badge = "Wholesale Pack"
        ),

        // Other Essentials
        Product(
            id = 20,
            name = "Bic Classic Disposable Shavers (Card of 24)",
            category = "Other Essentials",
            retailPrice = 960.0,
            wholesalePrice = 790.0,
            wholesaleMinQuantity = 5,
            unit = "Card of 24",
            stockQuantity = 110,
            inStock = true,
            description = "High precision single blade shavers for smooth everyday grooming. Wholesale card packaging for retail resale.",
            imageUrl = "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?auto=format&fit=crop&w=600&q=80",
            isFeatured = false,
            badge = "Resale Card"
        )
    )
}
