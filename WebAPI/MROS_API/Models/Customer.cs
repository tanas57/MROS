using System;
using System.ComponentModel.DataAnnotations;

namespace MROS_API.Models
{
    public class Customer
    {
        [Key]
        public int ID { get; set; }
        public string FullName { get; set; }
        public string Phone { get; set; }
        public string Address { get; set; }
        public DateTime Birhdate { get; set; }
        public bool Sex { get; set; }
        public DateTime RegDateTime { get; set; }
        public string ProfilePhoto { get; set; }
        public int OrderCount { get; set; }
        public bool Status { get; set; }
    }
}
