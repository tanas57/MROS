using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace MROS_API.Models
{
    public class Order
    {
        [Key]
        public int ID { get; set; }
        public Restaurant Restaurant { get; set; }
        public Table Table { get; set; }
        public ICollection<Product> Products { get; set; }
        public double TotalCost { get; set; }
        public bool Status { get; set; }
    }
}
