using Microsoft.EntityFrameworkCore;
using MROS_API.Models;

namespace MROS_API.Data
{
    public class ProjectContext : DbContext
    {
        public ProjectContext(DbContextOptions options) : base(options) { }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
        }
        public DbSet<Customer> Customers { get; set; }
        public DbSet<CustomerFeedBack> CustomerFeeds { get; set; }
        public DbSet<Order> Orders { get; set; }
        public DbSet<Portion> Portions { get; set; }
        public DbSet<Product> Products { get; set; }
        public DbSet<ProductDetail> ProductDetails { get; set; }
        public DbSet<Restaurant> Restaurants { get; set; }
        public DbSet<Table> Tables { get; set; }
        public DbSet<ProductCategory> ProductCategories { get; set; }
    }
}
