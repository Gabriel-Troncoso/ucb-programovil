package org.ucb.appp1.catalog.screen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.ucb.appp1.catalog.domain.model.PersonModel

@Composable
fun CharacterCard(
    person: PersonModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFE8E1F5), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = person.name.take(1).uppercase(),
                        color = Color(0xFF5E3A8C),
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = person.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            CharacterField("name", person.name)
            CharacterField("height", person.height)
            CharacterField("mass", person.mass)
            CharacterField("hair_color", person.hairColor)
            CharacterField("skin_color", person.skinColor)
            CharacterField("eye_color", person.eyeColor)
            CharacterField("gender", person.gender)

        }
    }
}

@Composable
private fun CharacterField(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
