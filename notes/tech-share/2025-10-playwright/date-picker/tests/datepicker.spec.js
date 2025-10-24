const { test, expect } = require('@playwright/test');

test.describe('Date Picker Form', () => {
  test('should populate start date and end date fields', async ({ page }) => {
    // Navigate to the HTML page
    await page.goto('http://localhost:6160/index.html');

    // Wait for datepicker to be initialized
    await page.waitForFunction(() => {
      return typeof $ !== 'undefined' && $('#startDate').hasClass('hasDatepicker');
    });

    // Set the start date using jQuery datepicker's setDate method
    await page.evaluate(() => {
      $('#startDate').datepicker('setDate', '2020-01-01');
    });

    // Set the end date using jQuery datepicker's setDate method
    await page.evaluate(() => {
      $('#endDate').datepicker('setDate', '2025-11-01');
    });

    // Verify the values were set correctly by checking the datepicker's getDate method
    const startDate = await page.evaluate(() => {
      return $('#startDate').datepicker('getDate')?.toISOString().split('T')[0];
    });

    const endDate = await page.evaluate(() => {
      return $('#endDate').datepicker('getDate')?.toISOString().split('T')[0];
    });

    expect(startDate).toBe('2020-01-01');
    expect(endDate).toBe('2025-11-01');
  });
});
