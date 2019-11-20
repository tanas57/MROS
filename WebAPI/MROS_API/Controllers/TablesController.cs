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
    [Route("v1/table/")]
    [ApiController]
    public class TablesController : ControllerBase
    {
        private readonly ProjectContext _context;
        
        public TablesController(ProjectContext context)
        {
            _context = context;
        }
        [Route("list")]
        [HttpGet]
        public IEnumerable<Table> GetTables()
        {
            return _context.Tables;
        }

        // GET: api/Tables/5
        [Route("{id}")]
        public async Task<IActionResult> GetTable([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var table = await _context.Tables.Include(x=> x.Restaurant).FirstOrDefaultAsync(x=> x.ID == id);

            if (table == null)
            {
                return NotFound();
            }

            return Ok(table);
        }

        [HttpGet("restaurant/{table_id}")]
        public async Task<IActionResult> GetRestaurantofTable([FromRoute] int table_id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var table = await _context.Tables.FindAsync(table_id);

            if (table == null)
            {
                return NotFound();
            }

            return Ok(table.Restaurant);
        }


        // PUT: api/Tables/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutTable([FromRoute] int id, [FromBody] Table table)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != table.ID)
            {
                return BadRequest();
            }

            _context.Entry(table).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TableExists(id))
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

        // POST: api/Tables
        [HttpPost]
        public async Task<IActionResult> PostTable([FromBody] Table table)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Tables.Add(table);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetTable", new { id = table.ID }, table);
        }

        // DELETE: api/Tables/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTable([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var table = await _context.Tables.FindAsync(id);
            if (table == null)
            {
                return NotFound();
            }

            _context.Tables.Remove(table);
            await _context.SaveChangesAsync();

            return Ok(table);
        }

        private bool TableExists(int id)
        {
            return _context.Tables.Any(e => e.ID == id);
        }
    }
}