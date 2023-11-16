#ifndef DRAW_H
#define DRAW_H



u8 derive_palette_index(u8 r, u8 g, u8 b);

void draw_player(int x, int y, int length, u8 *fb);

#endif