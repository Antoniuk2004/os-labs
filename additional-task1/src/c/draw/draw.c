#ifndef DRAW_H
#define DRAW_H

#include <stddef.h>
#include "../kernel/kernel.h"

#define SCREEN_WIDTH 640
#define SCREEN_HEIGHT 480

u32 palette[] = {
        0x00000, 0x000AA, 0x0AA00, 0x0AAAA, 0xAA0000, 0xAA00AA, 0xAA5500, 0xAAAAAA,
        0x555555, 0x5555FF, 0x55FF55, 0x55FFFF, 0xFF5555, 0xFF55FF, 0xFFFF55, 0xFFFFFF
};

void* memcpy(void* dest, const void* src, size_t count) {
    char* dest_c = (char*)dest;
    const char* src_c = (const char*)src;

    while (count--) {
        *dest_c++ = *src_c++;
    }

    return dest;
}

u8 derive_palette_index(u8 r, u8 g, u8 b) {
    int min_dist = 2147483647;
    u8 index = 0;

    for (int i = 0; i < sizeof(palette) / sizeof(palette[0]); i++) {
        u32 color = palette[i];

        u8 palette_r = (color >> 16) & 0xFF;
        u8 palette_g = (color >> 8) & 0xFF;
        u8 palette_b = color & 0xFF;

        int dr = r - palette_r;
        int dg = g - palette_g;
        int db = b - palette_b;

        int dist = dr * dr + dg * dg + db * db;
        if (dist < min_dist) {
            min_dist = dist;
            index = i;
        }
    }

    return index;
}

void draw_player(int x, int y, int length, u8 *fb) {
    int player[1024] = {

    };

    int arr[4] = {0x5, 0xf,
                  0xf,
                  0xf};


    int img_heigth = 32;
    int img_width = 32;
    for (int i = 0; i < img_width; i++) {
        for (int k = 0; k < img_heigth; k++) {
            *(fb + i + k * SCREEN_WIDTH) = player[img_width * i + k];
        }
    }
}

#endif
