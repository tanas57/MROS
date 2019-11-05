using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MROS_API.Models
{
    public class Table
    {
        [Key]
        public int ID { get; set; }
        public Restaurant Restaurant { get; set; }
        public string TableName { get; set; }
        public bool Status { get; set; }
    }
}
