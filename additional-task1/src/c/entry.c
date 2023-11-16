#include "kernel/kernel.h"
#include "drivers/keyboard/keyboard.h"
#include "drivers/timer/timer.h"
#include "drivers/serial_port/serial_port.h"
#include "kernel/mutliboot.h"
#include "draw/draw.h"

#define SCREEN_WIDTH 640
#define SCREEN_HEIGHT 480

void exception_handler(u32 interrupt, u32 error, char *message) {
    serial_log(LOG_ERROR, message);
}

void init_kernel() {
    init_gdt();
    init_idt();
    init_exception_handlers();
    init_interrupt_handlers();
    register_timer_interrupt_handler();
    register_keyboard_interrupt_handler();
    configure_default_serial_port();
    set_exception_handler(exception_handler);
    enable_interrupts();
}

// Standard 16 colors


/**
 * Returns the index in the pallet for color that is closest
 * to the given RGB values. Use returned result to write to framebuffer.
 */
//u8 derive_palette_index(u8 r, u8 g, u8 b) {
//    int min_dist = 2147483647;
//    u8 index = 0;
//
//    for (int i = 0; i < sizeof(palette) / sizeof(palette[0]); i++) {
//        u32 color = palette[i];
//
//        u8 palette_r = (color >> 16) & 0xFF;
//        u8 palette_g = (color >> 8) & 0xFF;
//        u8 palette_b = color & 0xFF;
//
//        int dr = r - palette_r;
//        int dg = g - palette_g;
//        int db = b - palette_b;
//
//        int dist = dr * dr + dg * dg + db * db;
//        if (dist < min_dist) {
//            min_dist = dist;
//            index = i;
//        }
//    }
//
//    return index;
//}

/**
 * Sets PIT divider, smaller value, more timer interrupts there will be.
 * The max frequency is around 1.2MHz, the provided divider divides it.
 * If you want to have an interrupt every 100ms (or 10 times per second or 10Hz)
 * you need to divide 1.2MHz by 10 which gives you = 119000 divider value.
 * If you use this function with 119000 value you will get timer interrupt every 100ms.
 */
void set_pit_divider(u16 divider) {
    out(0x43, 0x36);
    out(0x40, divider & 0xFF);
    out(0x40, (divider >> 8) & 0xFF);
}

/**
 * In order to avoid execution of arbitrary instructions by CPU we halt it.
 * Halt "pauses" CPU and puts it in low power mode until next interrupt occurs.
 */
_Noreturn void halt_loop() {
    while (1) { halt(); }
}

void key_handler(struct keyboard_event event) {

}

void on_timer_tick() {

}

/**
 * This is where the bootloader transfers control to.
 */
void kernel_entry(multiboot_info_t *mbi) {
    init_kernel();
    keyboard_set_handler(key_handler);
    timer_set_handler(on_timer_tick);

    u8 *fb = (u8 *) mbi->framebuffer_addr;

    draw_player(0, 0, 50, fb);

    // let's draw a blue diagonal to have some example
    // for (u32 i = 0; i < SCREEN_HEIGHT; i++) {
    //     *(fb + i * SCREEN_WIDTH + i) = derive_palette_index(255, 255, 255);
    // }


//    for (int i = 0; i < 50; i++) {
//        *(fb + i + x + y * SCREEN_WIDTH) = derive_palette_index(50, 255, 255);
//    }



    halt_loop();
}
