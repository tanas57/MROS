using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MROS_API.Models
{
    public class OrderDetail
    {
        [Key]
        public int ID { get; set; }
        public Order Order { get; set; }
        public Product Product { get; set; }
        public DateTime OrderTime { get; set; }
    }
}
