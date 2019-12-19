using System;
using System.ComponentModel.DataAnnotations;

namespace MROS_API.Models
{
    public class CustomerFeedBack
    {
        [Key]
        public int ID { get; set; }
        public Customer Owner { get; set; }
        public Restaurant Restaurant { get; set; }
        public double RatingService { get; set; }
        public double RatingWaiter { get; set; }
        public double RatingFlavor { get; set; }
        public double ServiceTime { get; set; }
        public string Message { get; set; }
        public DateTime FeedDateTime { get; set; }
    }
}
