using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MROS_API.Models
{
    public class Portion
    {
        [Key]
        public int ID { get; set; }
        public string PortionName { get; set; }
        public double Cost { get; set; }
    }
}
