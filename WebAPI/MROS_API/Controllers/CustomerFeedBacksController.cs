using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using MROS_API.Data;
using MROS_API.Models;

namespace MROS_API.Controllers
{
    [Route("v1/feeds/")]
    [ApiController]
    public class CustomerFeedBacksController : ControllerBase
    {
        private readonly ProjectContext _context;

        public CustomerFeedBacksController(ProjectContext context)
        {
            _context = context;
        }

        // GET: api/CustomerFeedBacks
        [HttpGet]
        public IEnumerable<CustomerFeedBack> GetCustomerFeeds()
        {
            return _context.CustomerFeeds;
        }

        // GET: api/CustomerFeedBacks/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetCustomerFeedBack([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var customerFeedBack = await _context.CustomerFeeds.FindAsync(id);

            if (customerFeedBack == null)
            {
                return NotFound();
            }

            return Ok(customerFeedBack);
        }

        // GET: api/Restaurants/5
        [HttpGet("restaurant/{id}")]
        public async Task<IActionResult> GetFeeds([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var feeds = await _context.CustomerFeeds.Where(x => x.Restaurant.ID == id).ToListAsync();

            if (feeds == null)
            {
                return NotFound();
            }

            return Ok(feeds);
        }


        // PUT: api/CustomerFeedBacks/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutCustomerFeedBack([FromRoute] int id, [FromBody] CustomerFeedBack customerFeedBack)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != customerFeedBack.ID)
            {
                return BadRequest();
            }

            _context.Entry(customerFeedBack).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CustomerFeedBackExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/CustomerFeedBacks
        [HttpPost]
        public async Task<IActionResult> PostCustomerFeedBack([FromBody] CustomerFeedBack customerFeedBack)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.CustomerFeeds.Add(customerFeedBack);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetCustomerFeedBack", new { id = customerFeedBack.ID }, customerFeedBack);
        }

        // DELETE: api/CustomerFeedBacks/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteCustomerFeedBack([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var customerFeedBack = await _context.CustomerFeeds.FindAsync(id);
            if (customerFeedBack == null)
            {
                return NotFound();
            }

            _context.CustomerFeeds.Remove(customerFeedBack);
            await _context.SaveChangesAsync();

            return Ok(customerFeedBack);
        }

        private bool CustomerFeedBackExists(int id)
        {
            return _context.CustomerFeeds.Any(e => e.ID == id);
        }
    }
}