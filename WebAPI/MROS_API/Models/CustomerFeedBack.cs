using System.ComponentModel.DataAnnotations;

namespace MROS_API.Models
{
    public class CustomerFeedBack
    {
        [Key]
        public int ID { get; set; }
        public Customer Owner { get; set; }
        public Restaurant Restaurant { get; set; }
        public double Rating { get; set; }
        public string Message { get; set; }
    }
}
