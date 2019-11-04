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

                var packages = GetPackages(context).ToArray(); ;
                context.Packages.AddRange(packages);
                context.SaveChanges();
            }
        }
        public static List<Customer> GetCustomers()
        {
            List<Customer> customers = new List<Customer>()
            {
                new Customer{ FullName = "Tayyip Muslu", Address = "Kuruçeşme Mah. 205/7 Sok. No 16/1 D 8 Buca/İzmir", 
                    Phone = "513213212", RegDateTime = DateTime.Now, Birhdate = new DateTime(1995,1,6),
                    Sex = true, OrderCount = 0, Status = true, ProfilePhoto = null}
                new Customer{ FullName = "Enes Demirdere", Address = "Atatürk Mah Buca/İzmir",
                    Phone = "54131312", RegDateTime = DateTime.Now, Birhdate = new DateTime(1995,3,11),
                    Sex = true, OrderCount = 0, Status = true, ProfilePhoto = null}
            };
            return customers;
        }

        public List<Table> GetTables()
        {

        }

        public List<Restaurant> GetRestaurants()
        {
            List<Restaurant> restaurants = new List<Restaurant>()
            {
                new Restaurant(){FullName = "Göl Pastanesi", Address = "Yabancı diller fakültesi kampüsü yakını",
                                 Information = "Ekmek ve unlu mamuller, tatlı vs.", Latitude = 37.0001, Longitude = 27.0002,
                                 Status = true}
            }
        }

        public Restaurant GetRestaurant(int id)
        {

        }

        public static List<Product> getBreakfastProduct()
        {
            List<Product> customers = new List<Product>()
            {
                new Product(){ Name = "Sade Poğaca", Price = 1.5, IsPortionable = false, Restaurant = }
            };
            return customers;
        }

        public static List<ProductCategory> GetProductCategories()
        {
            List<ProductCategory> customers = new List<ProductCategory>()
            {
                new ProductCategory(){CatName = "Kahvaltılar", Status = true, Counter = 0}
            };
            return customers;
        }

        public static List<Package> GetPackages(ProjectContext context)
        {
            List<Package> packages = new List<Package>()
            {
                new Package{ Barcode = 12345678910, Customer = context.Customers.FirstOrDefault(x=> x.Id == 1), PackageDesi = 2, PackageWeigth =3 },
                new Package{ Barcode = 12345678911, Customer = context.Customers.FirstOrDefault(x=> x.Id == 2), PackageDesi = 1, PackageWeigth = 5 },
                new Package{ Barcode = 12345678912, Customer = context.Customers.FirstOrDefault(x=> x.Id == 4), PackageDesi = 6, PackageWeigth =3 },
            };
            return packages;
        }
    }
}