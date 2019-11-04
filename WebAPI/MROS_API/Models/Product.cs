using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MROS_API.Models
{
    public class Product
    {
        [Key]
        public int ID { get; set; }
        public Restaurant Restaurant { get; set; }
        public string Name { get; set; }
        public double Price { get; set; }
        public ICollection<ProductDetail> Content { get; set; }
        public bool IsPortionable { get; set; }
        public ICollection<Portion> Portions { get; set; }
    }
}
