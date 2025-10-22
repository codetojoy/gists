import { test, expect } from '@playwright/test';

test.describe('Login Form Acceptance Tests', () => {
  
  test('should successfully login with valid credentials', async ({ page }) => {
    // Navigate to the login page
    await page.goto('https://example.com/login');
    
    // Find the login form elements
    const emailInput = page.locator('input[type="email"], input[name="email"], input#email');
    const passwordInput = page.locator('input[type="password"], input[name="password"], input#password');
    const loginButton = page.locator('button[type="submit"], button:has-text("Login"), input[type="submit"]');
    
    // Verify form elements are visible
    await expect(emailInput).toBeVisible();
    await expect(passwordInput).toBeVisible();
    await expect(loginButton).toBeVisible();
    
    // Fill in the login credentials
    await emailInput.fill('user@example.com');
    await passwordInput.fill('SecurePassword123!');
    
    // Submit the form
    await loginButton.click();
    
    // Wait for navigation after login
    await page.waitForURL('**/dashboard', { timeout: 5000 });
    
    // Verify successful login by checking for expected elements on the dashboard
    await expect(page.locator('h1:has-text("Dashboard")')).toBeVisible();
    
    // Optional: Verify user is authenticated (check for logout button, user menu, etc.)
    await expect(page.locator('[data-testid="user-menu"]')).toBeVisible();
  });

  test('should show error message with invalid credentials', async ({ page }) => {
    await page.goto('https://example.com/login');
    
    // Fill in invalid credentials
    await page.fill('input[type="email"]', 'invalid@example.com');
    await page.fill('input[type="password"]', 'wrongpassword');
    
    // Submit the form
    await page.click('button[type="submit"]');
    
    // Verify error message appears
    const errorMessage = page.locator('.error-message, [role="alert"], .alert-danger');
    await expect(errorMessage).toBeVisible();
    await expect(errorMessage).toContainText(/invalid|incorrect|wrong/i);
    
    // Verify we're still on the login page
    await expect(page).toHaveURL(/.*login/);
  });

  test('should validate required fields', async ({ page }) => {
    await page.goto('https://example.com/login');
    
    // Try to submit without filling in fields
    await page.click('button[type="submit"]');
    
    // Check for validation messages (HTML5 or custom)
    const emailInput = page.locator('input[type="email"]');
    
    // For HTML5 validation
    const validationMessage = await emailInput.evaluate(
      (el: HTMLInputElement) => el.validationMessage
    );
    expect(validationMessage).toBeTruthy();
  });
});
