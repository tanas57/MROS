using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MROS_API.Models
{
    public class ProductCategory
    {
        [Key]
        public int ID { get; set; }
        public string CatName { get; set; }
        public string CatIMG { get; set; }
        public Restaurant Restaurant { get; set; }
        public bool Status { get; set; }
        public int Counter { get; set; }
    }
}
