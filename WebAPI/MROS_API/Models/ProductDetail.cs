using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MROS_API.Models
{
    public class ProductDetail
    {
        [Key]
        public int ID { get; set; }
        public string Name { get; set; }
        public bool Status { get; set; }
    }
}
