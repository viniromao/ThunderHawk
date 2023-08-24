#version 330 core

in vec4 v_color;
in vec2 v_texCoords;

uniform sampler2D u_texture;
uniform float u_time; // For animation

out vec4 FragColor; // Declare an output variable for the fragment color

void main() {
    // Create an underwater wave effect (e.g., a simple sine wave pattern)
    float wave = sin(v_texCoords.y + u_time) * 0.1;

    // Alter the x coordinate of the texture coordinates based on the wave effect
    vec2 distortedTexCoords = vec2(v_texCoords.x + wave, v_texCoords.y);

    // Sample the texture using the distorted texture coordinates
    vec4 texColor = texture(u_texture, distortedTexCoords);

                                       FragColor = v_color * texColor; // Assign the final color to the output variable
                                       }
