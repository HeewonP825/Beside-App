package com.beside.hackathon.presentation.view.record



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import coil.compose.AsyncImage
import com.beside.hackathon.databinding.FragmentCardNewsRecordBinding
import com.beside.hackathon.presentation.view.common.DefaultLayout


class CardNewsRecordFragment : Fragment() {
    private var _binding: FragmentCardNewsRecordBinding? = null
    private lateinit var navController: NavController
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // navController 초기화
        navController = findNavController()
        _binding = FragmentCardNewsRecordBinding.inflate(inflater, container, false)
        val root = binding.root

        val urls = arguments?.getStringArrayList("urls")
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    ImageScreen(navController, urls!!)
                }
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


@Composable
fun ImageScreen(navController: NavController, imgs: List<String>){
    DefaultLayout (
        title = "카드뉴스",
        backButtonOnClick = { navController.popBackStack() }
    ){
        LazyColumn {
            items(imgs.size) {index->
                AsyncImage(
                    model = imgs[index],
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.FillWidth,
                )
            }
        }



    }
}