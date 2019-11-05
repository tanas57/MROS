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

                var tables = AddTables().ToArray();
                context.Tables.AddRange(tables);
                context.SaveChanges();

                var productCategories = GetProductCategories().ToArray();
                context.ProductCategories.AddRange(productCategories);
                context.SaveChanges();

                var products = GetProducts().ToArray();
                context.Products.AddRange(products);
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

        public static List<Table> AddTables()
        {
            List<Table> tables = new List<Table>()
            {
                new Table(){ Restaurant = GetRestaurant(0), TableName = "Bahçe 1", Status = true, },
                new Table(){ Restaurant = GetRestaurant(0), TableName = "Bahçe 2", Status = true, },
                new Table(){ Restaurant = GetRestaurant(0), TableName = "Bahçe 3", Status = true, },
                new Table(){ Restaurant = GetRestaurant(1), TableName = "Sağ Kısım 1", Status = true, },
                new Table(){ Restaurant = GetRestaurant(1), TableName = "Sağ Kısım 2", Status = true, },
                new Table(){ Restaurant = GetRestaurant(1), TableName = "Sağ Kısım 3", Status = true, },
                new Table(){ Restaurant = GetRestaurant(2), TableName = "İçeri A-1", Status = true, },
                new Table(){ Restaurant = GetRestaurant(2), TableName = "İçeri A-2", Status = true, },
                new Table(){ Restaurant = GetRestaurant(2), TableName = "VIP1", Status = true, }
            };
            return tables;
        }

        public static List<ProductCategory> GetProductCategories()
        {
            List<ProductCategory> productCategories = new List<ProductCategory>()
            {
                new ProductCategory(){ Restaurant = GetRestaurant(0), CatName = "Kahvaltılar", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = GetRestaurant(0), CatName = "Pastalar", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = GetRestaurant(0), CatName = "İçecekler", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = GetRestaurant(1), CatName = "Döner Ürünleri", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = GetRestaurant(1), CatName = "İçecekler", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = GetRestaurant(1), CatName = "Tatlılar", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = GetRestaurant(2), CatName = "Pideler", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = GetRestaurant(2), CatName = "Kebaplar", Counter = 0 , Status = true},
                new ProductCategory(){ Restaurant = GetRestaurant(2), CatName = "İçecekler", Counter = 0 , Status = true},
            };
            return productCategories;
        }

        public static ProductCategory GetProductCategory(int id)
        {
            return GetProductCategories()[id];
        }

        public static List<Product> GetProducts()
        {
            List<Product> products = new List<Product>()
            {
                new Product{ Restaurant = GetRestaurant(0), Name = "Sade Poğaca", Price = 1.5, IsPortionable = false, ProductCategory = GetProductCategory(0)},
                new Product{ Restaurant = GetRestaurant(0), Name = "Kaşarlı Poğaca", Price = 1.75, IsPortionable = false, ProductCategory = GetProductCategory(0)},
                new Product{ Restaurant = GetRestaurant(0), Name = "Zeytinli Poğaca", Price = 1.5, IsPortionable = false, ProductCategory = GetProductCategory(0)},
                new Product{ Restaurant = GetRestaurant(0), Name = "Çilekli Dilim Pasta", Price = 9.50, IsPortionable = false, ProductCategory = GetProductCategory(1)},
                new Product{ Restaurant = GetRestaurant(0), Name = "Traliçe", Price = 8.75, IsPortionable = false, ProductCategory = GetProductCategory(1)},
                new Product{ Restaurant = GetRestaurant(1), Name = "Katık", Price = 10, IsPortionable = false, ProductCategory = GetProductCategory(3)},
                new Product{ Restaurant = GetRestaurant(1), Name = "Antakya Döner", Price = 10, IsPortionable = false, ProductCategory = GetProductCategory(3)},
                new Product{ Restaurant = GetRestaurant(1), Name = "İskenderun Döner", Price = 10, IsPortionable = false, ProductCategory = GetProductCategory(3)},

            };
            return products;
        }


    }
}