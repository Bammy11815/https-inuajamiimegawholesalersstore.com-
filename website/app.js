/**
 * Inua Jamii Megawholers Store - Web E-Commerce Application Engine
 * Phone / WhatsApp: +254783831157
 * Email: megawholesalers12@gmail.com
 */

const STORE_CONFIG = {
  name: "Inua Jamii Megawholers Store",
  phone: "+254783831157",
  whatsappNumber: "254783831157",
  email: "megawholesalers12@gmail.com",
  website: "https://Inuajamiimegawholesalersstore/"
};

// Initial Authentic Kenyan Products
const DEFAULT_PRODUCTS = [
  {
    id: 1,
    name: "Pembe Maize Meal 2kg",
    category: "Flour & Cereals",
    retailPrice: 210,
    wholesalePrice: 175,
    wholesaleMinQty: 12,
    unit: "Bale / 2kg Packet",
    stock: 450,
    inStock: true,
    badge: "Wholesale Favorite",
    imageUrl: "https://images.unsplash.com/photo-1586201375761-83865001e31c?auto=format&fit=crop&w=600&q=80",
    description: "Grade 1 fortified sifted maize meal for rich, smooth Kenyan ugali. Packaged 12 x 2kg packets per wholesale bale."
  },
  {
    id: 2,
    name: "Jogoo Maize Flour 2kg",
    category: "Flour & Cereals",
    retailPrice: 205,
    wholesalePrice: 170,
    wholesaleMinQty: 12,
    unit: "2kg Packet",
    stock: 320,
    inStock: true,
    badge: "Best Seller",
    imageUrl: "https://images.unsplash.com/photo-1574323347407-f5e1ad6d020b?auto=format&fit=crop&w=600&q=80",
    description: "Kenya's classic fortified maize meal. Quick cooking, white flour perfect for daily duka resale."
  },
  {
    id: 3,
    name: "Ndovu Home Baking Flour 2kg",
    category: "Flour & Cereals",
    retailPrice: 225,
    wholesalePrice: 188,
    wholesaleMinQty: 12,
    unit: "2kg Packet",
    stock: 210,
    inStock: true,
    badge: "Hot Deal",
    imageUrl: "https://images.unsplash.com/photo-1509440159596-0249088772ff?auto=format&fit=crop&w=600&q=80",
    description: "Premium all-purpose wheat flour for mandazi, chapati, pastries and commercial bakeries."
  },
  {
    id: 4,
    name: "Daawat Long Grain Basmati Rice 5kg",
    category: "Food & Groceries",
    retailPrice: 1350,
    wholesalePrice: 1180,
    wholesaleMinQty: 6,
    unit: "5kg Bag",
    stock: 180,
    inStock: true,
    badge: "Top Pick",
    imageUrl: "https://images.unsplash.com/photo-1586201375761-83865001e31c?auto=format&fit=crop&w=600&q=80",
    description: "Finest quality aromatic long-grain basmati rice. Fluffy and fragrant for pilau and biryani."
  },
  {
    id: 5,
    name: "Rina Pure Vegetable Cooking Oil 5L",
    category: "Cooking Oil",
    retailPrice: 1280,
    wholesalePrice: 1090,
    wholesaleMinQty: 4,
    unit: "5L Jerrycan",
    stock: 140,
    inStock: true,
    badge: "Wholesale Deal",
    imageUrl: "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5?auto=format&fit=crop&w=600&q=80",
    description: "Triple-refined pure palm olein vegetable cooking oil. Fortified with vitamins A & D."
  },
  {
    id: 6,
    name: "Salit Cooking Oil 10L",
    category: "Cooking Oil",
    retailPrice: 2450,
    wholesalePrice: 2150,
    wholesaleMinQty: 2,
    unit: "10L Jerrycan",
    stock: 85,
    inStock: true,
    badge: "Bulk Special",
    imageUrl: "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5?auto=format&fit=crop&w=600&q=80",
    description: "Commercial size cooking oil designed for restaurants, fast foods, caterers and wholesale redistribution."
  },
  {
    id: 7,
    name: "Mumias Pure White Sugar 1kg",
    category: "Sugar & Salt",
    retailPrice: 175,
    wholesalePrice: 145,
    wholesaleMinQty: 20,
    unit: "1kg Pkt / Bale 20kg",
    stock: 500,
    inStock: true,
    badge: "Essential",
    imageUrl: "https://images.unsplash.com/photo-1622484216805-4c02eb962f2f?auto=format&fit=crop&w=600&q=80",
    description: "Crystalline natural pure cane sugar. 20 packets per bale."
  },
  {
    id: 8,
    name: "Kensalt Iodized Table Salt 1kg",
    category: "Sugar & Salt",
    retailPrice: 45,
    wholesalePrice: 34,
    wholesaleMinQty: 20,
    unit: "1kg Pkt / Bale 20kg",
    stock: 600,
    inStock: true,
    badge: "Everyday",
    imageUrl: "https://images.unsplash.com/photo-1518110903416-749e7b26d8ee?auto=format&fit=crop&w=600&q=80",
    description: "Fine grain vacuum refined iodized table salt."
  },
  {
    id: 9,
    name: "Ketepa Pride Tea Bags 100s",
    category: "Tea & Spices",
    retailPrice: 280,
    wholesalePrice: 230,
    wholesaleMinQty: 12,
    unit: "Box of 100 Bags",
    stock: 230,
    inStock: true,
    badge: "Best Seller",
    imageUrl: "https://images.unsplash.com/photo-1576092768241-dec231879fc3?auto=format&fit=crop&w=600&q=80",
    description: "Authentic rich Kenyan highland tea. Carton of 12 boxes."
  },
  {
    id: 10,
    name: "Royco Mchuzi Mix Beef 500g",
    category: "Tea & Spices",
    retailPrice: 230,
    wholesalePrice: 195,
    wholesaleMinQty: 12,
    unit: "500g Jar",
    stock: 160,
    inStock: true,
    badge: "Classic",
    imageUrl: "https://images.unsplash.com/photo-1596040033229-a9821ebd058d?auto=format&fit=crop&w=600&q=80",
    description: "A blend of herbs, spices, cornstarch and garlic for rich stew flavor."
  },
  {
    id: 11,
    name: "Brookside Whole Milk 500ml (Crate of 24)",
    category: "Beverages",
    retailPrice: 1680,
    wholesalePrice: 1450,
    wholesaleMinQty: 3,
    unit: "Crate 24 Pkts",
    stock: 90,
    inStock: true,
    badge: "Wholesale Crate",
    imageUrl: "https://images.unsplash.com/photo-1550583724-b2692b85b150?auto=format&fit=crop&w=600&q=80",
    description: "Homogenized long-life whole cow milk in secure wholesale crate."
  },
  {
    id: 12,
    name: "Omo Hand Washing Powder 1kg",
    category: "Cleaning Products",
    retailPrice: 330,
    wholesalePrice: 275,
    wholesaleMinQty: 10,
    unit: "1kg Pkt",
    stock: 220,
    inStock: true,
    badge: "Hot Deal",
    imageUrl: "https://images.unsplash.com/photo-1583947215259-38e31be8751f?auto=format&fit=crop&w=600&q=80",
    description: "Extra foam fast stain removal detergent powder."
  },
  {
    id: 13,
    name: "Sunlight Dishwashing Liquid Lemon 750ml",
    category: "Cleaning Products",
    retailPrice: 260,
    wholesalePrice: 215,
    wholesaleMinQty: 12,
    unit: "750ml Bottle",
    stock: 175,
    inStock: true,
    badge: null,
    imageUrl: "https://images.unsplash.com/photo-1584813470613-5b1c1cad3d69?auto=format&fit=crop&w=600&q=80",
    description: "Real lemon extract grease cutter for sparkling utensils."
  },
  {
    id: 14,
    name: "Geisha Bathing Soap 225g (Pack of 3)",
    category: "Personal Care",
    retailPrice: 320,
    wholesalePrice: 270,
    wholesaleMinQty: 12,
    unit: "Pack of 3 Bars",
    stock: 180,
    inStock: true,
    badge: "Family Pack",
    imageUrl: "https://images.unsplash.com/photo-1607082348824-0a96f2a4b9da?auto=format&fit=crop&w=600&q=80",
    description: "Long-lasting family beauty soap with natural extract nourishment."
  },
  {
    id: 15,
    name: "Velvex Soft Toilet Tissue 10 Rolls",
    category: "Household Products",
    retailPrice: 540,
    wholesalePrice: 450,
    wholesaleMinQty: 6,
    unit: "Pack of 10 Rolls",
    stock: 150,
    inStock: true,
    badge: "Wholesale Pack",
    imageUrl: "https://images.unsplash.com/photo-1584556812952-905ffd0c611a?auto=format&fit=crop&w=600&q=80",
    description: "2-ply embossed virgin wood pulp bathroom tissue."
  },
  {
    id: 16,
    name: "Bic Classic Shavers (Card of 24)",
    category: "Other Essentials",
    retailPrice: 960,
    wholesalePrice: 790,
    wholesaleMinQty: 5,
    unit: "Card of 24",
    stock: 110,
    inStock: true,
    badge: "Resale Card",
    imageUrl: "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?auto=format&fit=crop&w=600&q=80",
    description: "Precision single-blade shavers hung on display retail card."
  }
];

const CATEGORIES_DATA = [
  { name: "Food & Groceries", icon: "🍚", desc: "Rice, pulses, spices, staple flour, canned food" },
  { name: "Cooking Oil", icon: "🛢️", desc: "Pure vegetable oils in 3L, 5L, 10L, 20L jerrycans" },
  { name: "Flour & Cereals", icon: "🌾", desc: "Maize meal & all-purpose wheat flour bales" },
  { name: "Sugar & Salt", icon: "🧂", desc: "Pure cane sugar bales and iodized table salt" },
  { name: "Tea & Spices", icon: "☕", desc: "Kenyan highland black tea & stew spices" },
  { name: "Beverages", icon: "🥤", desc: "UHT milk crates and carbonated soft drink cases" },
  { name: "Cleaning Products", icon: "🧼", desc: "Washing powder, dish liquids & laundry bars" },
  { name: "Personal Care", icon: "🧴", desc: "Family bath soaps, toothpastes & hygiene items" },
  { name: "Household Products", icon: "🧻", desc: "Toilet tissues, serviettes & foil" },
  { name: "Other Essentials", icon: "📦", desc: "Shavers, matches, batteries & store supplies" }
];

const REVIEWS_DATA = [
  {
    name: "Mary Wanjiku",
    role: "Duka Owner, Thika",
    rating: 5,
    text: "Inua Jamii Megawholesalers has transformed my shop restocking! Their wholesale bale prices for Pembe and Mumias sugar are the lowest around, and delivery to Thika was prompt."
  },
  {
    name: "Ahmed Hassan",
    role: "Restaurant Manager, Eastleigh",
    rating: 5,
    text: "Ordering 10L cooking oil crates and rice via WhatsApp takes only two minutes. We save over KSh 8,000 every single month on wholesale tiers."
  },
  {
    name: "Grace Achieng",
    role: "Household Shopper, Nairobi",
    rating: 5,
    text: "Paying on delivery via M-Pesa gave me peace of mind. Genuine brands, well-packaged, and friendly drivers. Highly recommended!"
  }
];

const FAQS_DATA = [
  {
    q: "How does Wholesale vs Retail pricing work?",
    a: "All items have two transparent tiers: 1 to 11 units are sold at standard retail price. When you order 12 or more units of an item, the wholesale rate automatically unlocks in your cart, saving you 15% to 25% per unit!"
  },
  {
    q: "How does delivery work across Kenya's 47 counties?",
    a: "We offer same-day and next-day doorstep delivery within Nairobi and neighboring counties (Kiambu, Machakos, Kajiado). Upcountry shipments (Mombasa, Kisumu, Nakuru, Eldoret, etc.) are dispatched via trusted logistics partners (Fargo Courier, Wells Fargo, and bus parcel SACCOs)."
  },
  {
    q: "What payment methods do you accept?",
    a: "We accept M-Pesa on Delivery (inspect goods first, then pay), official M-Pesa Till / Paybill, and Cash on Delivery for Nairobi deliveries."
  },
  {
    q: "Can I order directly on WhatsApp?",
    a: "Yes! Simply tap 'Order via WhatsApp' on any product or click the floating WhatsApp button to chat directly with our sales desk on +254783831157."
  },
  {
    q: "Are the commodities and brands 100% genuine?",
    a: "Yes, 100%. We source directly from authorized Kenyan FMCG manufacturers including Pembe, Unga Ltd, Pwani Oil, Bidco, Kensalt, and Unilever."
  }
];

// App State Management (with LocalStorage)
let products = JSON.parse(localStorage.getItem("ijm_products")) || DEFAULT_PRODUCTS;
let cart = JSON.parse(localStorage.getItem("ijm_cart")) || [];
let orders = JSON.parse(localStorage.getItem("ijm_orders")) || [];
let quotes = JSON.parse(localStorage.getItem("ijm_quotes")) || [];
let messages = JSON.parse(localStorage.getItem("ijm_messages")) || [];

// Save to storage
function saveState() {
  localStorage.setItem("ijm_products", JSON.stringify(products));
  localStorage.setItem("ijm_cart", JSON.stringify(cart));
  localStorage.setItem("ijm_orders", JSON.stringify(orders));
  localStorage.setItem("ijm_quotes", JSON.stringify(quotes));
  localStorage.setItem("ijm_messages", JSON.stringify(messages));
  updateCartCounters();
}

// Format Kenyan Currency
function formatKsh(amount) {
  return "KSh " + Math.round(amount).toLocaleString();
}

// Router
function navigateTo(pageId) {
  document.querySelectorAll(".page-section").forEach(sec => sec.classList.remove("active"));
  const target = document.getElementById("page-" + pageId);
  if (target) {
    target.classList.add("active");
    window.scrollTo({ top: 0, behavior: "smooth" });
  }

  // Update Nav links active state
  document.querySelectorAll(".nav-item").forEach(a => {
    if (a.getAttribute("href") === "#" + pageId) {
      a.classList.add("active");
    } else {
      a.classList.remove("active");
    }
  });

  // Mobile menu close
  document.getElementById("main-nav").classList.remove("mobile-open");

  // Page specific renders
  if (pageId === "shop") renderShopProducts();
  if (pageId === "cart") renderCart();
  if (pageId === "checkout") renderCheckoutSummary();
  if (pageId === "admin") renderAdminView();
}

function toggleMobileNav() {
  document.getElementById("main-nav").classList.toggle("mobile-open");
}

// Show Toast
function showToast(msg) {
  const toast = document.getElementById("toast-notification");
  toast.innerText = msg;
  toast.classList.add("show");
  setTimeout(() => toast.classList.remove("show"), 3200);
}

// Cart Logic
function addToCart(productId, qty = 1) {
  const product = products.find(p => p.id === productId);
  if (!product) return;

  const existing = cart.find(c => c.productId === productId);
  if (existing) {
    existing.quantity += qty;
  } else {
    cart.push({
      productId: product.id,
      name: product.name,
      category: product.category,
      unit: product.unit,
      retailPrice: product.retailPrice,
      wholesalePrice: product.wholesalePrice,
      wholesaleMinQty: product.wholesaleMinQty,
      imageUrl: product.imageUrl,
      quantity: qty
    });
  }
  saveState();
  showToast(`Added ${qty} ${product.unit} of ${product.name} to cart!`);
}

function updateCartQty(productId, newQty) {
  if (newQty <= 0) {
    removeFromCart(productId);
    return;
  }
  const item = cart.find(c => c.productId === productId);
  if (item) {
    item.quantity = newQty;
    saveState();
    renderCart();
  }
}

function removeFromCart(productId) {
  cart = cart.filter(c => c.productId !== productId);
  saveState();
  renderCart();
  showToast("Item removed from cart");
}

function clearCart() {
  if (confirm("Are you sure you want to clear your cart?")) {
    cart = [];
    saveState();
    renderCart();
  }
}

function getCartCalculations() {
  let subtotal = 0;
  let savings = 0;
  let totalItems = 0;

  cart.forEach(item => {
    const isWholesale = item.quantity >= item.wholesaleMinQty;
    const unitPrice = isWholesale ? item.wholesalePrice : item.retailPrice;
    subtotal += unitPrice * item.quantity;
    totalItems += item.quantity;
    if (isWholesale) {
      savings += (item.retailPrice - item.wholesalePrice) * item.quantity;
    }
  });

  const deliveryFee = subtotal >= 10000 || subtotal === 0 ? 0 : 350;
  const total = subtotal + deliveryFee;

  return { subtotal, savings, deliveryFee, total, totalItems };
}

function updateCartCounters() {
  const calc = getCartCalculations();
  document.getElementById("cart-counter").innerText = calc.totalItems;
  document.getElementById("header-cart-total").innerText = formatKsh(calc.subtotal);
}

// Render Products Card HTML
function createProductCardHTML(product) {
  const savings = Math.max(0, product.retailPrice - product.wholesalePrice);
  return `
    <div class="product-card">
      <div class="product-media" onclick="openProductById(${product.id})">
        ${product.imageUrl ? `<img src="${product.imageUrl}" alt="${product.name}">` : `<div class="product-placeholder-icon">📦</div>`}
        ${product.badge ? `<span class="product-badge-overlay">${product.badge}</span>` : ""}
        <span class="product-stock-tag ${product.inStock ? 'stock-in' : 'stock-out'}">
          ${product.inStock ? 'In Stock' : 'Sold Out'}
        </span>
      </div>
      <div class="product-body">
        <span class="product-category-label">${product.category}</span>
        <h3 class="product-title" onclick="openProductById(${product.id})">${product.name}</h3>
        <p class="product-unit-text">Unit: ${product.unit}</p>

        <div class="product-pricing-box">
          <div class="price-row wholesale-price-row">
            <span>Wholesale (${product.wholesaleMinQty}+):</span>
            <span class="price-val-wholesale">${formatKsh(product.wholesalePrice)}</span>
          </div>
          <div class="price-row">
            <span>Retail (1-${product.wholesaleMinQty - 1}):</span>
            <span>${formatKsh(product.retailPrice)}</span>
          </div>
          ${savings > 0 ? `<div class="savings-tag">Save ${formatKsh(savings)}/unit on wholesale</div>` : ""}
        </div>

        <div class="product-card-actions">
          <button class="btn btn-whatsapp btn-sm" onclick="orderWhatsAppDirect(${product.id})">💬 WhatsApp</button>
          <button class="btn btn-primary btn-sm" onclick="addToCart(${product.id}, 1)">+ Add</button>
        </div>
      </div>
    </div>
  `;
}

// Render Product Details Modal
function openProductById(id) {
  const p = products.find(x => x.id === id);
  if (!p) return;

  const savings = Math.max(0, p.retailPrice - p.wholesalePrice);
  const modalContent = document.getElementById("modal-product-content");
  modalContent.innerHTML = `
    <div style="display: grid; grid-template-columns: 1fr 1.2fr; gap: 24px; align-items: start;">
      <div style="background: #F3F4F6; border-radius: 12px; overflow: hidden; height: 260px;">
        ${p.imageUrl ? `<img src="${p.imageUrl}" style="width:100%; height:100%; object-fit:cover;">` : `<div style="font-size:64px; display:flex; align-items:center; justify-content:center; height:100%;">📦</div>`}
      </div>
      <div>
        <span style="color:var(--primary); font-size:11px; font-weight:700; text-transform:uppercase;">${p.category}</span>
        <h2 style="font-size:22px; margin:4px 0 6px 0;">${p.name}</h2>
        <p style="font-size:12px; color:var(--slate-500); margin-bottom:14px;">Packaging Unit: ${p.unit} • In Stock: ${p.stock} units</p>

        <div style="background:var(--slate-50); border:1px solid var(--slate-200); border-radius:8px; padding:12px; margin-bottom:16px;">
          <div style="display:flex; justify-content:space-between; margin-bottom:6px;">
            <span>Retail Rate (1–${p.wholesaleMinQty - 1} units):</span>
            <strong>${formatKsh(p.retailPrice)}</strong>
          </div>
          <div style="display:flex; justify-content:space-between; color:var(--primary); font-size:15px; font-weight:800;">
            <span>Wholesale Rate (${p.wholesaleMinQty}+ units):</span>
            <span>${formatKsh(p.wholesalePrice)}</span>
          </div>
          ${savings > 0 ? `<p style="font-size:11px; color:var(--accent-gold); font-weight:700; margin-top:4px;">Save ${formatKsh(savings)} per unit automatically!</p>` : ""}
        </div>

        <div style="display:flex; align-items:center; gap:12px; margin-bottom:16px;">
          <label style="font-size:13px; font-weight:700;">Quantity:</label>
          <div class="qty-stepper">
            <button onclick="decrementModalQty()">-</button>
            <input type="number" id="modal-qty-input" value="1" min="1" onchange="updateModalLivePrice(${p.id})">
            <button onclick="incrementModalQty()">+</button>
          </div>
          <button class="btn btn-outline btn-sm" onclick="setModalQty(${p.wholesaleMinQty}, ${p.id})">${p.wholesaleMinQty} pcs (Wholesale)</button>
        </div>

        <div id="modal-total-calc" style="font-size:15px; font-weight:700; color:var(--primary); margin-bottom:16px;">
          Total: ${formatKsh(p.retailPrice)} (Retail Rate)
        </div>

        <div style="display:flex; flex-direction:column; gap:8px;">
          <button class="btn btn-whatsapp" onclick="orderModalWhatsApp(${p.id})">💬 Order via WhatsApp</button>
          <button class="btn btn-primary" onclick="addModalToCart(${p.id})">Add to Shopping Cart</button>
        </div>

        <div style="margin-top:16px; font-size:12.5px; color:var(--slate-600); line-height:1.5;">
          <strong>Description:</strong><br>
          ${p.description || "High-grade consumer product sourced directly from accredited Kenyan producers."}
        </div>
      </div>
    </div>
  `;

  document.getElementById("product-detail-modal").classList.add("open");
}

function closeProductModal(event) {
  if (!event || event.target.id === "product-detail-modal" || event.target.classList.contains("modal-close")) {
    document.getElementById("product-detail-modal").classList.remove("open");
  }
}

function decrementModalQty() {
  const input = document.getElementById("modal-qty-input");
  let val = parseInt(input.value) || 1;
  if (val > 1) {
    input.value = val - 1;
    input.dispatchEvent(new Event("change"));
  }
}

function incrementModalQty() {
  const input = document.getElementById("modal-qty-input");
  let val = parseInt(input.value) || 1;
  input.value = val + 1;
  input.dispatchEvent(new Event("change"));
}

function setModalQty(qty, prodId) {
  const input = document.getElementById("modal-qty-input");
  input.value = qty;
  updateModalLivePrice(prodId);
}

function updateModalLivePrice(prodId) {
  const p = products.find(x => x.id === prodId);
  const qty = parseInt(document.getElementById("modal-qty-input").value) || 1;
  const isWholesale = qty >= p.wholesaleMinQty;
  const unitPrice = isWholesale ? p.wholesalePrice : p.retailPrice;
  const total = unitPrice * qty;

  const target = document.getElementById("modal-total-calc");
  target.innerHTML = `Total: ${formatKsh(total)} <span style="font-size:12px; color:${isWholesale ? 'var(--primary)' : 'var(--slate-500)'};">(${isWholesale ? 'Wholesale Price Applied' : 'Retail Price'})</span>`;
}

function addModalToCart(prodId) {
  const qty = parseInt(document.getElementById("modal-qty-input").value) || 1;
  addToCart(prodId, qty);
  document.getElementById("product-detail-modal").classList.remove("open");
}

function orderModalWhatsApp(prodId) {
  const p = products.find(x => x.id === prodId);
  const qty = parseInt(document.getElementById("modal-qty-input").value) || 1;
  const isWholesale = qty >= p.wholesaleMinQty;
  const unitPrice = isWholesale ? p.wholesalePrice : p.retailPrice;
  const total = unitPrice * qty;

  const text = `Hello Inua Jamii Megawholers Store!%0AI would like to order:%0A• Product: ${encodeURIComponent(p.name)}%0A• Quantity: ${qty} ${encodeURIComponent(p.unit)}%0A• Rate: ${isWholesale ? 'Wholesale' : 'Retail'} (${formatKsh(unitPrice)} each)%0A• Total: ${formatKsh(total)}%0APlease confirm delivery details.`;
  window.open(`https://wa.me/${STORE_CONFIG.whatsappNumber}?text=${text}`, "_blank");
}

function orderWhatsAppDirect(prodId) {
  const p = products.find(x => x.id === prodId);
  if (!p) return;
  const qty = p.wholesaleMinQty;
  const total = p.wholesalePrice * qty;

  const text = `Hello Inua Jamii Megawholers Store!%0AI want to order via WhatsApp:%0A• Product: ${encodeURIComponent(p.name)}%0A• Quantity: ${qty} ${encodeURIComponent(p.unit)} (Wholesale)%0A• Price: ${formatKsh(p.wholesalePrice)} each%0A• Total: ${formatKsh(total)}%0APlease confirm delivery schedule.`;
  window.open(`https://wa.me/${STORE_CONFIG.whatsappNumber}?text=${text}`, "_blank");
}

// Global Search
function handleGlobalSearch(event) {
  if (event.key === "Enter") {
    executeSearch();
  }
}

function executeSearch() {
  const query = document.getElementById("global-search-input").value.trim();
  navigateTo("shop");
  document.getElementById("shop-search").value = query;
  filterProducts();
}

// Filter Products
function filterProducts() {
  const query = (document.getElementById("shop-search").value || "").toLowerCase().trim();
  const category = document.getElementById("shop-category-select").value;
  const sort = document.getElementById("shop-sort-select").value;
  const wholesaleOnly = document.getElementById("shop-wholesale-only").checked;

  let filtered = [...products];

  if (category !== "All") {
    filtered = filtered.filter(p => p.category === category);
  }

  if (wholesaleOnly) {
    filtered = filtered.filter(p => p.wholesalePrice < p.retailPrice);
  }

  if (query) {
    filtered = filtered.filter(p =>
      p.name.toLowerCase().includes(query) ||
      p.category.toLowerCase().includes(query) ||
      p.description.toLowerCase().includes(query)
    );
  }

  if (sort === "price-low") {
    filtered.sort((a, b) => a.retailPrice - b.retailPrice);
  } else if (sort === "price-high") {
    filtered.sort((a, b) => b.retailPrice - a.retailPrice);
  } else if (sort === "name") {
    filtered.sort((a, b) => a.name.localeCompare(b.name));
  } else {
    filtered.sort((a, b) => (b.badge ? 1 : 0) - (a.badge ? 1 : 0));
  }

  const container = document.getElementById("shop-products-grid");
  const counter = document.getElementById("shop-results-count");

  counter.innerText = `Showing ${filtered.length} products`;

  if (filtered.length === 0) {
    container.innerHTML = `
      <div style="grid-column: 1/-1; text-align:center; padding: 48px 0; color:var(--slate-500);">
        <div style="font-size:48px;">🔍</div>
        <h3 style="margin-top:12px;">No products match your search</h3>
        <p>Try searching with another keyword or resetting the category filter.</p>
      </div>
    `;
    return;
  }

  container.innerHTML = filtered.map(p => createProductCardHTML(p)).join("");
}

function filterByCategory(cat) {
  navigateTo("shop");
  document.getElementById("shop-category-select").value = cat;
  filterProducts();
}

// Render Home Screen Components
function renderHomeContent() {
  // 1. Categories Grid
  const catGrid = document.getElementById("home-category-grid");
  catGrid.innerHTML = CATEGORIES_DATA.slice(0, 10).map(c => `
    <div class="cat-card" onclick="filterByCategory('${c.name}')">
      <div class="cat-icon">${c.icon}</div>
      <h4>${c.name}</h4>
      <span class="cat-count">Wholesale & Retail</span>
    </div>
  `).join("");

  // 2. Featured Deals
  const featGrid = document.getElementById("home-featured-products-grid");
  featGrid.innerHTML = products.slice(0, 8).map(p => createProductCardHTML(p)).join("");

  // 3. Reviews
  const revGrid = document.getElementById("home-reviews-grid");
  revGrid.innerHTML = REVIEWS_DATA.map(r => `
    <div class="review-card">
      <div class="review-stars">★★★★★</div>
      <p class="review-text">"${r.text}"</p>
      <div class="review-author">${r.name}</div>
      <div class="review-role">${r.role}</div>
    </div>
  `).join("");
}

// Render Full Categories Page
function renderCategoriesPage() {
  const grid = document.getElementById("categories-full-grid");
  grid.innerHTML = CATEGORIES_DATA.map(c => {
    const count = products.filter(p => p.category === c.name).length;
    return `
      <div class="cat-card" style="padding:24px; text-align:left; display:flex; gap:18px; align-items:center;" onclick="filterByCategory('${c.name}')">
        <div class="cat-icon" style="font-size:42px; margin:0;">${c.icon}</div>
        <div>
          <h3 style="font-size:17px; margin-bottom:4px;">${c.name}</h3>
          <p style="font-size:12px; color:var(--slate-600); margin-bottom:6px;">${c.desc}</p>
          <span style="font-size:11px; font-weight:700; color:var(--primary);">${count} Products in Stock →</span>
        </div>
      </div>
    `;
  }).join("");
}

// Render Shop Page
function renderShopProducts() {
  filterProducts();
}

// Render Wholesale Page
function renderWholesalePage() {
  const grid = document.getElementById("wholesale-products-grid");
  const wholesaleList = products.filter(p => p.wholesalePrice < p.retailPrice);
  grid.innerHTML = wholesaleList.map(p => createProductCardHTML(p)).join("");
}

// Render Reviews Page
function renderReviewsPage() {
  const grid = document.getElementById("full-reviews-grid");
  grid.innerHTML = REVIEWS_DATA.map(r => `
    <div class="review-card">
      <div class="review-stars">★★★★★</div>
      <p class="review-text">"${r.text}"</p>
      <div class="review-author">${r.name}</div>
      <div class="review-role">${r.role}</div>
    </div>
  `).join("");
}

// Render FAQ Page
function renderFaqPage() {
  const cont = document.getElementById("faq-accordion-container");
  cont.innerHTML = FAQS_DATA.map((f, i) => `
    <div class="faq-item ${i === 0 ? 'open' : ''}" onclick="toggleFaq(this)">
      <div class="faq-question">
        <span>${f.q}</span>
        <span class="faq-arrow">▼</span>
      </div>
      <div class="faq-answer">
        <p>${f.a}</p>
      </div>
    </div>
  `).join("");
}

function toggleFaq(el) {
  el.classList.toggle("open");
}

// Render Shopping Cart
function renderCart() {
  const cont = document.getElementById("cart-container-view");
  const calc = getCartCalculations();

  if (cart.length === 0) {
    cont.innerHTML = `
      <div style="grid-column: 1/-1; text-align:center; padding: 60px 0;">
        <div style="font-size:64px;">🛒</div>
        <h2 style="margin: 14px 0 6px 0;">Your Shopping Cart is Empty</h2>
        <p style="color:var(--slate-500); margin-bottom: 24px;">Explore our catalog to find essential Kenyan food commodities and household goods.</p>
        <button class="btn btn-primary" onclick="navigateTo('shop')">Start Shopping Now</button>
      </div>
    `;
    return;
  }

  const itemsHTML = cart.map(item => {
    const isWholesale = item.quantity >= item.wholesaleMinQty;
    const unitPrice = isWholesale ? item.wholesalePrice : item.retailPrice;
    const lineTotal = unitPrice * item.quantity;
    const needed = Math.max(0, item.wholesaleMinQty - item.quantity);

    return `
      <div class="cart-item-row">
        <div class="cart-thumb">
          ${item.imageUrl ? `<img src="${item.imageUrl}" alt="${item.name}">` : `📦`}
        </div>
        <div class="cart-item-details">
          <h4>${item.name}</h4>
          <span style="font-size:11px; color:var(--slate-500);">${item.unit}</span><br>
          <span class="cart-tier-chip ${isWholesale ? 'tier-wholesale-chip' : 'tier-retail-chip'}">
            ${isWholesale ? `Wholesale Rate: ${formatKsh(unitPrice)}` : `Retail Rate: ${formatKsh(unitPrice)}`}
          </span>
          ${!isWholesale && needed > 0 ? `<p style="font-size:10px; color:var(--accent-gold); font-weight:700; margin-top:2px;">Add ${needed} more for wholesale price (${formatKsh(item.wholesalePrice)})</p>` : ""}
        </div>
        <div class="qty-stepper">
          <button onclick="updateCartQty(${item.productId}, ${item.quantity - 1})">-</button>
          <input type="number" value="${item.quantity}" readonly>
          <button onclick="updateCartQty(${item.productId}, ${item.quantity + 1})">+</button>
        </div>
        <div class="cart-price-col">
          ${formatKsh(lineTotal)}
        </div>
        <button class="cart-del-btn" onclick="removeFromCart(${item.productId})" title="Remove item">🗑️</button>
      </div>
    `;
  }).join("");

  cont.innerHTML = `
    <div class="cart-items-table">
      ${itemsHTML}
    </div>
    <div class="summary-box">
      <h3>Order Summary</h3>
      <div class="summary-line">
        <span>Subtotal (${calc.totalItems} items):</span>
        <strong>${formatKsh(calc.subtotal)}</strong>
      </div>
      ${calc.savings > 0 ? `
        <div class="summary-line highlight">
          <span>Wholesale Savings:</span>
          <span>- ${formatKsh(calc.savings)}</span>
        </div>
      ` : ""}
      <div class="summary-line">
        <span>Estimated Delivery Fee:</span>
        <span>${calc.deliveryFee === 0 ? '<strong style="color:var(--primary);">FREE</strong>' : formatKsh(calc.deliveryFee)}</span>
      </div>
      <div class="summary-line summary-total">
        <span>Total Payable:</span>
        <span style="color:var(--primary);">${formatKsh(calc.total)}</span>
      </div>
      <div class="summary-actions">
        <button class="btn btn-primary full-width" onclick="navigateTo('checkout')">Proceed to Checkout →</button>
        <button class="btn btn-whatsapp full-width" onclick="sendCartDirectWhatsApp()">💬 Order Entire Cart via WhatsApp</button>
        <button class="btn btn-text full-width" onclick="navigateTo('shop')">← Continue Shopping</button>
      </div>
    </div>
  `;
}

function sendCartDirectWhatsApp() {
  const calc = getCartCalculations();
  if (cart.length === 0) return;

  let text = `Hello Inua Jamii Megawholers Store!%0AI would like to order my shopping cart:%0A----------------------------%0A`;
  cart.forEach(item => {
    const isW = item.quantity >= item.wholesaleMinQty;
    const price = isW ? item.wholesalePrice : item.retailPrice;
    text += `• ${encodeURIComponent(item.name)} x ${item.quantity} ${encodeURIComponent(item.unit)} (${isW ? 'Wholesale' : 'Retail'}) = ${formatKsh(price * item.quantity)}%0A`;
  });
  text += `----------------------------%0ASubtotal: ${formatKsh(calc.subtotal)}%0AEstimated Total: ${formatKsh(calc.total)}%0APlease confirm payment (M-Pesa) and delivery details.`;

  window.open(`https://wa.me/${STORE_CONFIG.whatsappNumber}?text=${text}`, "_blank");
}

// Render Checkout Summary
function renderCheckoutSummary() {
  const calc = getCartCalculations();
  const cont = document.getElementById("checkout-summary-view");

  cont.innerHTML = `
    <h3>Order Summary (${calc.totalItems} items)</h3>
    <div style="max-height: 220px; overflow-y:auto; margin-bottom: 14px;">
      ${cart.map(item => {
        const isW = item.quantity >= item.wholesaleMinQty;
        const price = isW ? item.wholesalePrice : item.retailPrice;
        return `
          <div style="display:flex; justify-content:space-between; font-size:12px; padding:4px 0; border-bottom:1px solid var(--slate-100);">
            <span>${item.name} x ${item.quantity}</span>
            <strong>${formatKsh(price * item.quantity)}</strong>
          </div>
        `;
      }).join("")}
    </div>
    <div class="summary-line">
      <span>Subtotal:</span>
      <span>${formatKsh(calc.subtotal)}</span>
    </div>
    ${calc.savings > 0 ? `
      <div class="summary-line highlight">
        <span>Wholesale Savings:</span>
        <span>- ${formatKsh(calc.savings)}</span>
      </div>
    ` : ""}
    <div class="summary-line">
      <span>Delivery:</span>
      <span>${calc.deliveryFee === 0 ? 'FREE' : formatKsh(calc.deliveryFee)}</span>
    </div>
    <div class="summary-line summary-total">
      <span>Total:</span>
      <span>${formatKsh(calc.total)}</span>
    </div>
  `;
}

// Handle Checkout Submission
function handleCheckoutSubmit(e) {
  e.preventDefault();
  const calc = getCartCalculations();
  if (cart.length === 0) {
    alert("Your cart is empty!");
    return;
  }

  const name = document.getElementById("checkout-name").value.trim();
  const phone = document.getElementById("checkout-phone").value.trim();
  const email = document.getElementById("checkout-email").value.trim();
  const county = document.getElementById("checkout-county").value;
  const town = document.getElementById("checkout-town").value.trim();
  const address = document.getElementById("checkout-address").value.trim();
  const instructions = document.getElementById("checkout-instructions").value.trim();
  const paymentMethod = document.querySelector('input[name="payment-method"]:checked').value;

  const orderNum = "IJM-" + Math.floor(1000 + Math.random() * 9000);

  const order = {
    id: Date.now(),
    orderNumber: orderNum,
    customerName: name,
    customerPhone: phone,
    customerEmail: email,
    county,
    town,
    address,
    instructions,
    paymentMethod,
    items: [...cart],
    subtotal: calc.subtotal,
    deliveryFee: calc.deliveryFee,
    total: calc.total,
    status: "Pending",
    createdAt: new Date().toISOString()
  };

  orders.unshift(order);
  cart = [];
  saveState();

  // Show Confirmation Modal
  document.getElementById("confirm-order-id").innerText = `Order #${orderNum}`;
  document.getElementById("confirm-order-text").innerText = `Thank you ${name}. Your order has been placed successfully for ${formatKsh(order.total)}. Our dispatch desk will contact you on ${phone}.`;

  const waBtn = document.getElementById("confirm-whatsapp-btn");
  waBtn.onclick = function() {
    let text = `Hello Inua Jamii Megawholers Store!%0AI have placed Order #${orderNum}:%0A• Customer: ${encodeURIComponent(name)}%0A• Phone: ${encodeURIComponent(phone)}%0A• Delivery Destination: ${encodeURIComponent(town)}, ${encodeURIComponent(county)} (${encodeURIComponent(address)})%0A• Payment: ${encodeURIComponent(paymentMethod)}%0A• Total: ${formatKsh(order.total)}%0APlease confirm receipt and delivery time.`;
    window.open(`https://wa.me/${STORE_CONFIG.whatsappNumber}?text=${text}`, "_blank");
  };

  document.getElementById("order-confirm-modal").classList.add("open");
}

function closeConfirmModal() {
  document.getElementById("order-confirm-modal").classList.remove("open");
  navigateTo("home");
}

// Bulk Quote Submission
function handleQuoteSubmit(e) {
  e.preventDefault();
  const business = document.getElementById("quote-business").value.trim();
  const name = document.getElementById("quote-name").value.trim();
  const phone = document.getElementById("quote-phone").value.trim();
  const email = document.getElementById("quote-email").value.trim();
  const location = document.getElementById("quote-location").value.trim();
  const units = document.getElementById("quote-units").value;
  const items = document.getElementById("quote-items").value.trim();
  const notes = document.getElementById("quote-notes").value.trim();

  const quote = {
    id: Date.now(),
    business,
    name,
    phone,
    email,
    location,
    units,
    items,
    notes,
    status: "Pending",
    createdAt: new Date().toISOString()
  };

  quotes.unshift(quote);
  saveState();
  showToast(`Wholesale quote submitted for ${business}! We will call you shortly.`);
  document.getElementById("quote-request-form").reset();
}

function sendQuoteDirectWhatsApp() {
  const business = document.getElementById("quote-business").value.trim() || "My Business";
  const name = document.getElementById("quote-name").value.trim() || "Customer";
  const location = document.getElementById("quote-location").value.trim() || "Kenya";
  const items = document.getElementById("quote-items").value.trim() || "Bulk Staples";

  const text = `Hello Inua Jamii Megawholers Store,%0AI would like a wholesale bulk quotation:%0A• Business: ${encodeURIComponent(business)}%0A• Contact: ${encodeURIComponent(name)}%0A• Location: ${encodeURIComponent(location)}%0A• Items Needed: ${encodeURIComponent(items)}%0APlease send your bulk rates.`;
  window.open(`https://wa.me/${STORE_CONFIG.whatsappNumber}?text=${text}`, "_blank");
}

// Contact Form
function handleContactSubmit(e) {
  e.preventDefault();
  const name = document.getElementById("contact-name").value.trim();
  const phone = document.getElementById("contact-phone").value.trim();
  const email = document.getElementById("contact-email").value.trim();
  const message = document.getElementById("contact-message").value.trim();

  messages.unshift({
    id: Date.now(),
    name,
    phone,
    email,
    message,
    createdAt: new Date().toISOString()
  });

  saveState();
  showToast(`Thank you ${name}! Your message has been sent.`);
  document.getElementById("contact-form").reset();
}

// Admin Operations
function renderAdminView() {
  document.getElementById("admin-prod-count").innerText = products.length;
  document.getElementById("admin-orders-count").innerText = orders.length;
  document.getElementById("admin-quotes-count").innerText = quotes.length;
  document.getElementById("admin-messages-count").innerText = messages.length;

  // Products Table
  const tbody = document.getElementById("admin-products-table-body");
  tbody.innerHTML = products.map(p => `
    <tr>
      <td><strong>${p.name}</strong><br><small style="color:var(--slate-500);">${p.unit}</small></td>
      <td>${p.category}</td>
      <td>${formatKsh(p.retailPrice)}</td>
      <td><strong style="color:var(--primary);">${formatKsh(p.wholesalePrice)}</strong></td>
      <td>${p.stock}</td>
      <td>
        <span class="product-stock-tag ${p.inStock ? 'stock-in' : 'stock-out'}" style="position:static; cursor:pointer;" onclick="toggleAdminStock(${p.id})">
          ${p.inStock ? 'In Stock' : 'Out of Stock'}
        </span>
      </td>
      <td>
        <button class="btn btn-outline btn-sm" onclick="editProductModal(${p.id})">Edit</button>
        <button class="btn btn-outline btn-sm" style="color:red;" onclick="deleteProduct(${p.id})">Delete</button>
      </td>
    </tr>
  `).join("");

  // Orders Tab
  const ordersCont = document.getElementById("admin-orders-list-view");
  if (orders.length === 0) {
    ordersCont.innerHTML = `<p style="padding:24px; text-align:center; color:var(--slate-500);">No orders placed yet.</p>`;
  } else {
    ordersCont.innerHTML = orders.map(o => `
      <div style="background:var(--white); border:1px solid var(--slate-200); border-radius:8px; padding:16px; margin-bottom:12px;">
        <div style="display:flex; justify-content:space-between; margin-bottom:8px;">
          <div>
            <strong>Order #${o.orderNumber}</strong> (${o.paymentMethod})
            <p style="font-size:12px; color:var(--slate-500);">Customer: ${o.customerName} • ${o.customerPhone} • ${o.town}, ${o.county}</p>
          </div>
          <div>
            <strong style="font-size:16px; color:var(--primary);">${formatKsh(o.total)}</strong><br>
            <select onchange="updateOrderStatus('${o.orderNumber}', this.value)" style="padding:4px 8px; font-size:11px;">
              <option value="Pending" ${o.status === 'Pending' ? 'selected' : ''}>Pending</option>
              <option value="Confirmed" ${o.status === 'Confirmed' ? 'selected' : ''}>Confirmed</option>
              <option value="Processing" ${o.status === 'Processing' ? 'selected' : ''}>Processing</option>
              <option value="Dispatched" ${o.status === 'Dispatched' ? 'selected' : ''}>Dispatched</option>
              <option value="Delivered" ${o.status === 'Delivered' ? 'selected' : ''}>Delivered</option>
            </select>
          </div>
        </div>
        <div style="font-size:12px; background:var(--slate-50); padding:8px; border-radius:6px; margin-bottom:8px;">
          ${o.items ? o.items.map(it => `${it.name} x ${it.quantity} (${formatKsh(it.retailPrice * it.quantity)})`).join(" • ") : ""}
        </div>
        <a href="https://wa.me/${o.customerPhone.replace(/[^0-9]/g, '')}?text=Hello%20${encodeURIComponent(o.customerName)},%20this%20is%20Inua%20Jamii%20Megawholesalers%20Store%20regarding%20Order%20#${o.orderNumber}" target="_blank" class="btn btn-whatsapp btn-sm">
          💬 WhatsApp Customer
        </a>
      </div>
    `).join("");
  }

  // Quotes Tab
  const quotesCont = document.getElementById("admin-quotes-list-view");
  quotesCont.innerHTML = quotes.length === 0 ? `<p style="padding:24px; text-align:center; color:var(--slate-500);">No bulk quote requests.</p>` : quotes.map(q => `
    <div style="background:var(--white); border:1px solid var(--slate-200); border-radius:8px; padding:16px; margin-bottom:12px;">
      <div style="display:flex; justify-content:space-between;">
        <strong>${q.business} (${q.units} units)</strong>
        <span style="font-size:11px; color:var(--slate-500);">${new Date(q.createdAt).toLocaleDateString()}</span>
      </div>
      <p style="font-size:12px; color:var(--slate-600); margin:4px 0;">Contact: ${q.name} • ${q.phone} • ${q.location}</p>
      <p style="font-size:13px; font-weight:600; color:var(--primary); margin:6px 0;">Items: ${q.items}</p>
      <a href="https://wa.me/${q.phone.replace(/[^0-9]/g, '')}?text=Hello%20${encodeURIComponent(q.name)},%20Inua%20Jamii%20Megawholers%20Store%20is%20replying%20to%20your%20quote%20request." target="_blank" class="btn btn-whatsapp btn-sm">
        💬 Send Quote via WhatsApp
      </a>
    </div>
  `).join("");

  // Messages Tab
  const msgCont = document.getElementById("admin-messages-list-view");
  msgCont.innerHTML = messages.length === 0 ? `<p style="padding:24px; text-align:center; color:var(--slate-500);">No contact messages.</p>` : messages.map(m => `
    <div style="background:var(--white); border:1px solid var(--slate-200); border-radius:8px; padding:14px; margin-bottom:10px;">
      <strong>${m.name} (${m.phone})</strong>
      <p style="font-size:12px; color:var(--slate-700); margin-top:4px;">"${m.message}"</p>
    </div>
  `).join("");
}

function switchAdminTab(tabName) {
  document.querySelectorAll(".admin-tab-btn").forEach(btn => btn.classList.remove("active"));
  document.querySelectorAll(".admin-tab-panel").forEach(p => p.classList.remove("active"));

  event.target.classList.add("active");
  document.getElementById("admin-tab-" + tabName).classList.add("active");
}

function toggleAdminStock(id) {
  const p = products.find(x => x.id === id);
  if (p) {
    p.inStock = !p.inStock;
    saveState();
    renderAdminView();
  }
}

function updateOrderStatus(orderNum, status) {
  const o = orders.find(x => x.orderNumber === orderNum);
  if (o) {
    o.status = status;
    saveState();
    showToast(`Order #${orderNum} status set to ${status}`);
  }
}

function deleteProduct(id) {
  if (confirm("Are you sure you want to delete this product?")) {
    products = products.filter(p => p.id !== id);
    saveState();
    renderAdminView();
    showToast("Product deleted");
  }
}

function openAddProductModal() {
  document.getElementById("admin-modal-title").innerText = "Add Product";
  document.getElementById("edit-product-id").value = "";
  document.getElementById("admin-product-form").reset();
  document.getElementById("admin-product-modal").classList.add("open");
}

function editProductModal(id) {
  const p = products.find(x => x.id === id);
  if (!p) return;

  document.getElementById("admin-modal-title").innerText = "Edit Product";
  document.getElementById("edit-product-id").value = p.id;
  document.getElementById("edit-product-name").value = p.name;
  document.getElementById("edit-product-category").value = p.category;
  document.getElementById("edit-product-retail").value = p.retailPrice;
  document.getElementById("edit-product-wholesale").value = p.wholesalePrice;
  document.getElementById("edit-product-unit").value = p.unit;
  document.getElementById("edit-product-stock").value = p.stock;
  document.getElementById("edit-product-min-wholesale").value = p.wholesaleMinQty;
  document.getElementById("edit-product-badge").value = p.badge || "";
  document.getElementById("edit-product-image").value = p.imageUrl || "";
  document.getElementById("edit-product-desc").value = p.description || "";
  document.getElementById("edit-product-instock").checked = p.inStock;

  document.getElementById("admin-product-modal").classList.add("open");
}

function closeAdminProductModal(event) {
  if (!event || event.target.id === "admin-product-modal" || event.target.classList.contains("modal-close") || event.target.innerText === "Cancel") {
    document.getElementById("admin-product-modal").classList.remove("open");
  }
}

function saveAdminProduct(e) {
  e.preventDefault();
  const id = document.getElementById("edit-product-id").value;
  const name = document.getElementById("edit-product-name").value.trim();
  const category = document.getElementById("edit-product-category").value;
  const retailPrice = parseFloat(document.getElementById("edit-product-retail").value);
  const wholesalePrice = parseFloat(document.getElementById("edit-product-wholesale").value);
  const unit = document.getElementById("edit-product-unit").value.trim() || "Piece";
  const stock = parseInt(document.getElementById("edit-product-stock").value) || 100;
  const wholesaleMinQty = parseInt(document.getElementById("edit-product-min-wholesale").value) || 12;
  const badge = document.getElementById("edit-product-badge").value.trim() || null;
  const imageUrl = document.getElementById("edit-product-image").value.trim();
  const description = document.getElementById("edit-product-desc").value.trim();
  const inStock = document.getElementById("edit-product-instock").checked;

  if (id) {
    // Edit existing
    const existing = products.find(x => x.id === parseInt(id));
    if (existing) {
      Object.assign(existing, { name, category, retailPrice, wholesalePrice, unit, stock, wholesaleMinQty, badge, imageUrl, description, inStock });
    }
  } else {
    // Add new
    products.push({
      id: Date.now(),
      name,
      category,
      retailPrice,
      wholesalePrice,
      unit,
      stock,
      wholesaleMinQty,
      badge,
      imageUrl,
      description,
      inStock
    });
  }

  saveState();
  closeAdminProductModal();
  renderAdminView();
  showToast("Product saved successfully!");
}

// Initial Boot
document.addEventListener("DOMContentLoaded", () => {
  renderHomeContent();
  renderCategoriesPage();
  renderWholesalePage();
  renderReviewsPage();
  renderFaqPage();
  updateCartCounters();
});
