using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using MROS_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MROS_API.Data
{
    public class DbHelper
    {
        public static void Initialize(IApplicationBuilder applicationBuilder)
        {
            using (var serviceScope = applicationBuilder.ApplicationServices.GetService<IServiceScopeFactory>().CreateScope())
            {
                var context = serviceScope.ServiceProvider.GetService<ProjectContext>();
                context.Database.EnsureCreated();

                if (context.Customers != null && context.Customers.Any())
                    return;

                var customers = GetCustomers().ToArray();
                context.Customers.AddRange(customers);
                context.SaveChanges();

                var restaurants = GetRestaurants().ToArray();
                context.Restaurants.AddRange(restaurants);
                context.SaveChanges();

                var tables = AddTables(context).ToArray();
                context.Tables.AddRange(tables);
                context.SaveChanges();

                var productCategories = GetProductCategories(context).ToArray();
                context.ProductCategories.AddRange(productCategories);
                context.SaveChanges();

                var products = GetProducts(context).ToArray();
                context.Products.AddRange(products);
                context.SaveChanges();

                var feeds = GetUserFeeds(context).ToArray();
                context.CustomerFeeds.AddRange(feeds);
                context.SaveChanges();


            }
        }
        public static List<Customer> GetCustomers()
        {
            List<Customer> customers = new List<Customer>()
            {
                new Customer{ FullName = "Tayyip Muslu", Address = "Kuruçeşme Mah. 205/7 Sok. No 16/1 D 8 Buca/İzmir",
                    Phone = "513213212", RegDateTime = DateTime.Now, Birhdate = new DateTime(1995,1,6),
                    Sex = true, OrderCount = 0, Status = true, ProfilePhoto = null},
                new Customer{ FullName = "Enes Demirdere", Address = "Atatürk Mah Buca/İzmir",
                    Phone = "54131312", RegDateTime = DateTime.Now, Birhdate = new DateTime(1995,3,11),
                    Sex = true, OrderCount = 0, Status = true, ProfilePhoto = null}
            };
            return customers;
        }

        public static List<Restaurant> GetRestaurants()
        {
            List<Restaurant> restaurants = new List<Restaurant>()
            {
                new Restaurant(){FullName = "Göl Pastanesi", Address = "Yabancı diller fakültesi kampüsü yakını",
                                 Information = "Ekmek ve unlu mamuller, tatlı vs.", Latitude = 37.0001, Longitude = 27.0002,
                                 Status = true, TotalGain = 0, OrderCount = 0, Phone = "2325553322"},
                new Restaurant(){FullName = "Katık", Address = "Göl pastanesi yanı antakya dönercisi",
                                 Information = "Hatay ve antakya usulü döner yiyebileceğiniz mekan.", Latitude = 37.0001, Longitude = 27.0002,
                                 Status = true, TotalGain = 0, OrderCount = 0, Phone = "2325553333"},
                new Restaurant(){FullName = "Gözde Pide", Address = "Hoca Ahmet Yesevi Caddesi Buca İzmir",
                                 Information = "Lahmacun, pide ve kebap mekanı", Latitude = 37.0001, Longitude = 27.0002,
                                 Status = true, TotalGain = 0, OrderCount = 0, Phone = "2325551111"}
            };

            return restaurants;

        }

        public static Restaurant GetRestaurant(int id)
        {
            return GetRestaurants()[id];
        }

        public static List<Table> AddTables(ProjectContext db)
        {
            List<Table> tables = new List<Table>()
            {
                new Table(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), TableName = "Bahçe 1", Status = true, },
                new Table(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), TableName = "Bahçe 2", Status = true, },
                new Table(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), TableName = "Bahçe 3", Status = true, },
                new Table(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), TableName = "Sağ Kısım 1", Status = true, },
                new Table(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), TableName = "Sağ Kısım 2", Status = true, },
                new Table(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), TableName = "Sağ Kısım 3", Status = true, },
                new Table(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 3), TableName = "İçeri A-1", Status = true, },
                new Table(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 3), TableName = "İçeri A-2", Status = true, },
                new Table(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 3), TableName = "SALON B1", Status = true, }
            };
            return tables;
        }

        public static List<ProductCategory> GetProductCategories(ProjectContext db)
        {
            List<ProductCategory> productCategories = new List<ProductCategory>()
            {
                new ProductCategory(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), CatName = "Kahvaltılar", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), CatName = "Pastalar", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), CatName = "İçecekler", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), CatName = "Döner Ürünleri", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), CatName = "İçecekler", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), CatName = "Tatlılar", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 3), CatName = "Pideler", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 3), CatName = "Kebaplar", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 3), CatName = "İçecekler", Counter = 0 , Status = true},
            };
            return productCategories;
        }


        public static List<Product> GetProducts(ProjectContext db)
        {
            List<Product> products = new List<Product>()
            {
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Sade Poğaca", Price = 1.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 1), Preparation = 0},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Kaşarlı Poğaca", Price = 1.75, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 1), Preparation = 3},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Zeytinli Poğaca", Price = 1.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 1), Preparation = 3},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Çilekli Dilim Pasta", Price = 9.50, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 2), Preparation = 3},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Traliçe", Price = 8.75, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 2), Preparation = 3},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Ekler", Price = 2.75, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 2), Preparation = 3},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "4 Kişilik Pasta", Price = 27.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 2), Preparation = 3},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), Name = "Katık", Price = 10, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 4), Preparation = 2},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), Name = "Antakya Döner", Price = 10, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 4), Preparation = 2},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), Name = "İskenderun Döner", Price = 10, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 4), Preparation = 2},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Boyoz", Price = 1.25, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 1), Preparation = 0},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Gevrek", Price = 1.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 1), Preparation = 0},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Su Böreği", Price = 3.35, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 1), Preparation = 0},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Dilim Pizza", Price = 4.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 1), Preparation = 0},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Kıymalı Börek", Price = 5.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 1), Preparation = 0},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Çay", Price = 2.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 3), Preparation = 0},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Duble Çay", Price = 3.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 3), Preparation = 0},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Neskafe", Price = 4.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 3), Preparation = 3},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), Name = "Türk Kahvesi", Price = 7.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 3), Preparation = 5},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), Name = "Küçük Ayran", Price = 1.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 5), Preparation = 0},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), Name = "Büyük Ayran", Price = 2, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 5), Preparation = 0},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), Name = "Kola", Price = 2.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 5), Preparation = 0},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), Name = "Puding", Price = 3.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 6), Preparation = 0},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), Name = "Kalburabastı", Price = 4, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 6), Preparation = 0},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), Name = "Künefe", Price = 9.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 6), Preparation = 20},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 3), Name = "Açık Pide", Price = 13.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 7), Preparation = 20},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 3), Name = "Adana Kebap", Price = 17.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 8), Preparation = 15},
                new Product{ Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 3), Name = "Ayran", Price = 2.5, IsPortionable = false, ProductCategory = db.ProductCategories.FirstOrDefault(x=> x.ID == 9), Preparation = 0},
            };
            return products;
        }

        public static List<CustomerFeedBack> GetUserFeeds(ProjectContext db)
        {
            List<CustomerFeedBack> customerFeeds = new List<CustomerFeedBack>()
            {
                new CustomerFeedBack(){ Owner = db.Customers.FirstOrDefault(x=> x.ID == 1),
                    Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), ServiceTime = 9, RatingFlavor = 8,
                    RatingService = 9, RatingWaiter = 7, Message = "Oldukça lezzetli ve çeşit bol olan bir restorant, sürekli tercihimiz olmaya devam ediyot" },
                 new CustomerFeedBack(){ Owner = db.Customers.FirstOrDefault(x=> x.ID == 1),
                    Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), ServiceTime = 5, RatingFlavor = 4,
                    RatingService = 7, RatingWaiter = 7, Message = "Niyeyse bugün performanslarından memnun kalmadık. Sanırım ilişkimizi gözden geçirmemeiz gerekecek :)" },
                                 new CustomerFeedBack(){ Owner = db.Customers.FirstOrDefault(x=> x.ID == 2),
                    Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), ServiceTime = 9, RatingFlavor = 8,
                    RatingService = 9, RatingWaiter = 7, Message = "Deneme yorum, ya pastane güzel emme pahalı.." },
            };
            return customerFeeds;
        }
    }
}