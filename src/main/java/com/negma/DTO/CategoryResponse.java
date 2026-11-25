package com.negma.DTO;

import com.negma.Entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {

    public UUID CategoryId;

    public String CategoryName;


}
