#ifndef __CIRCULAR_BUFFER_H
#define __CIRCULAR_BUFFER_H

typedef struct _cbuffer_t
{
	uint8_t*	values;
	int16_t		size;
	int16_t		read;
	int16_t     write;
} cbuffer_t;

typedef enum { eOK, eErrorBufferFull, eErrorBufferEmpty } cberror_t;

uint16_t buffer_length (cbuffer_t* buffer);
cberror_t buffer_write(cbuffer_t* buffer, uint8_t element);
cberror_t buffer_read(cbuffer_t* buffer, uint8_t *element);
void buffer_init(cbuffer_t* buffer, uint8_t *elements, int size);
#endif
