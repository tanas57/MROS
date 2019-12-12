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
                new Restaurant(){FullName = "Göl Pastanesi", Address = "Adatepe, Kıbrıs Cd. No:34, 35400 Buca/İzmir | Yabancı diller fakültesi kampüsü yakını",
                                 Information = @"Pelit ailesi yıllar önce Rize Çamlıhemşin’den Kırım’a çalışmaya gitti. Bölge, pastacılıkta çok ünlüydü. Pastacılığın bütün inceliklerini öğrendikten sonra kendi pastane zincirini kurdu. Ancak Bolşevik İhtilali ile birlikte memleketleri Rize’ye geri döndüler.

1946’da gittikleri Zonguldak’ta Balkaya Pastanesi’nde çalışmaya başladılar ve kısa bir süre sonra pastanenin işletmesini aldılar. Pastacılığın inceliklerini İstanbul’daki Moskova Pastanesi’nin yetişmiş ustalarından öğrendiler. Zonguldak’ın ardından atılımları için 1954’te İzmir’i tercih ettiler. İzmir Kıbrıs Şehitleri Caddesi’nde önce Ülkü Pastanesi’ni açan Pelit ailesi, 1957 yılında da Sevinç Pastanesi’ni açtılar.

Türkiye’nin çeşitli şehirlerinden en meşhur pasta, şekerleme, tatlı ustalarını transfer ederek; izmirlileri, baton muzlu pasta, çilekli pasta, turta, kestane şekeri, meyve şekerlemeleri, badem kurabiyesi, dilim pasta, krem şanti ve meyveli pasta gibi yeni lezzetlerle tanıştırdılar. İzmirliler tarafından büyük ilgi gören Sevinç Pastanesi, İzmir’in sembolü ve buluşma noktası haline geldi.

Günümüzde ailenin ikinci ve üçüncü kuşakları aldıkları bayrağı yeni lezzetler de katarak geleceğe taşımaktadırlar.

Türkiye’nin neresine gidilirse gidilsin mutlaka Sevinç lezzetlerini tatmış biri vardır.", Latitude = 37.0001, Longitude = 27.0002,
                                 Status = true, TotalGain = 0, OrderCount = 0, Phone = "0 232 555 3322", Photo = "gol-pastanesi.jpg"},
                new Restaurant(){FullName = "Katık", Address = "Adatepe, Kıbrıs Cd. No:32A, 35400 Buca/İzmir | Göl pastanesi yanı antakya dönercisi",
                                 Information = @"İskenderun döneri antakya döneriyle en fazla akraba olabilir, o da uzaktan. antakya dönerinde yediğiniz dönerden tat alırsınız, iskenderun dönerinde ise antakya dönerinde olmayan yeşillik vardır bol bol. o gevrek ekmeğin içinde dürüm şeklinde gelen döneri yedikten sonra normal ekmek arası döneri canınız bile çekmez. çünkü yavan gelir size. hatta otobüsle antakyadan getirtmişiğim bile vardır. zaman zaman kadınların bişeyleri aşermesi gibi canımın çektiği olur, ankarada pıtırak gibi çoğalan iskenderun döneri yenerek nefs kandırılmaya çalışılır ancak henüz başarılamamıştır. illa bi kulp takarsınız. neticede ne karnınız doyar ne gözünüz, içinizden söverek çıkarsınız mekandan. antakyaya girerken -hala ordamıdır bilmem- eğitim fakültesinin olduğu yerin yakınındaki tadım döner'in şişman döner ustasına selam ederim.
", Latitude = 37.0001, Longitude = 27.0002,
                                 Status = true, TotalGain = 0, OrderCount = 0, Phone = "0 232 555 3333", Photo = "katik.jpg"},
                new Restaurant(){FullName = "Gözde Pide", Address = "Hoca Ahmet Yesevi Caddesi Buca İzmir",
                                 Information = "Lahmacun, pide ve kebap mekanı Lahmacun, pide ve kebap mekanı Lahmacun, pide ve kebap mekanı Lahmacun, pide ve kebap mekanı Lahmacun, pide ve kebap mekanı Lahmacun, pide ve kebap mekanı Lahmacun, pide ve kebap mekanı Lahmacun, pide ve kebap mekanı Lahmacun, pide ve kebap mekanı ", Latitude = 37.0001, Longitude = 27.0002,
                                 Status = true, TotalGain = 0, OrderCount = 0, Phone = "0 232 555 1111", Photo = "gozde.jpg"}
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
                new CustomerFeedBack(){ Owner = db.Customers.FirstOrDefault(x=> x.ID == 1),
                    Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 1), ServiceTime = 9, RatingFlavor = 8,
                    RatingService = 9, RatingWaiter = 7, Message = "Deneme yorum, ya pastane güzel emme pahalı.." },
                new CustomerFeedBack(){ Owner = db.Customers.FirstOrDefault(x=> x.ID == 2),
                    Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), ServiceTime = 9, RatingFlavor = 8,
                    RatingService = 9, RatingWaiter = 7, Message = "antakyadan gelen lezetler.. gayet doyurucu ve ekonomik öğrenci dostu beğenerek yiyoruz." },
                new CustomerFeedBack(){ Owner = db.Customers.FirstOrDefault(x=> x.ID == 1),
                    Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), ServiceTime = 5, RatingFlavor = 4,
                    RatingService = 7, RatingWaiter = 7, Message = "Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum Katık yorum " },
                new CustomerFeedBack(){ Owner = db.Customers.FirstOrDefault(x=> x.ID == 2),
                    Restaurant = db.Restaurants.FirstOrDefault(x=> x.ID == 2), ServiceTime = 9, RatingFlavor = 8,
                    RatingService = 9, RatingWaiter = 7, Message = "Antakya adana usulu döner yedik içerisinden kıl çıktı hiç memnun kalmadık.. daha temiz ve titiz olmanızı talep ediyoruz bir kere daha şans vereceğiz umarım tekrar aynı şeyle karşılaşmayız." },
            };
            return customerFeeds;
        }
    }
}