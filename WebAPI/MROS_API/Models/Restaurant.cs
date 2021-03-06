﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace MROS_API.Models
{
    public class Restaurant
    {
        [Key]
        public int ID { get; set; }
        public string FullName { get; set; }
        public string Address { get; set; }
        public string Phone { get; set; }
        public string Logo { get; set; }
        public string Photo { get; set; }
        public string Information { get; set; }
        public double Latitude { get; set; }
        public double Longitude { get; set; }
        public bool Status { get; set; }
        public int OrderCount { get; set; }
        public double TotalGain { get; set; }
    }
}
